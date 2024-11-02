package com.memo.service.impl;


import com.memo.dto.UserDto;
import com.memo.entity.Company;
import com.memo.entity.User;
import com.memo.exception.UserDoesNotExistException;
import com.memo.repository.UserRepository;
import com.memo.service.UserService;
import com.memo.util.MapperUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,  MapperUtil mapperUtil1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.mapperUtil = mapperUtil1;
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new NoSuchElementException("There is no user with given id");
        }
        UserDto dto = mapperUtil.convert(user, new UserDto());
        dto.setIsOnlyAdmin(checkIfOnlyAdminForCompany(dto));
        return dto;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return mapperUtil.convert(user, new UserDto());
    }

//    @Override
//    public List<UserDto> getFilteredUsers() {
//        List<User> userList;
////        if (securityService.getLoggedInUser().getRole().getDescription().equals("Root User")) {
////            userList = userRepository.findAllByRole_Description("Admin");
////        } else {
////            userList = userRepository.findAllByCompany(mapperUtil.convert(securityService.getLoggedInUser().getCompany(), new Company()));
////        }
//        return userList.stream()
//                .sorted(Comparator.comparing((User u) -> u.getCompany().getTitle()).thenComparing(u -> u.getRole().getDescription()))
//                .map(entity -> mapperUtil.convert(entity, new UserDto()))
//                .peek(dto -> dto.setIsOnlyAdmin(this.checkIfOnlyAdminForCompany(dto)))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<UserDto> getAllUsers() throws Exception {
        List<User> userList=userRepository.findAll();
        return userList.stream()
                .map(entity -> mapperUtil.convert(entity, new UserDto()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = mapperUtil.convert(userDto, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return mapperUtil.convert(user, userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User updatedUser = mapperUtil.convert(userDto, new User());
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        updatedUser.setEnabled(userRepository.findUserById(userDto.getId()).isEnabled());
        User savedUser = userRepository.save(updatedUser);
        return mapperUtil.convert(savedUser, userDto);
    }

    @Override
    public void delete(Long userId) throws UserDoesNotExistException {
        User user = userRepository.findUserById(userId);
        user.setUsername(user.getUsername() + "-" + user.getId());  // without this modification, if entity has column(unique=true)
                                                                    // and we want to save a user with same email, it throws exception.
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public Boolean emailExist(UserDto userDto) {
        User userWithUpdatedEmail = userRepository.findByUsername(userDto.getUsername());
        if (userWithUpdatedEmail == null) return false;
        return !userWithUpdatedEmail.getId().equals(userDto.getId());
    }

    @Override
    public boolean userIdExist(Long userId) {
        User user=userRepository.findUserById(userId);
        return user != null;
    }


    private Boolean checkIfOnlyAdminForCompany(UserDto dto) {
        return userRepository.countAllByCompanyAndRole_Description(mapperUtil.convert(dto.getCompany(), new Company()), "Admin") == 1;
    }


}
