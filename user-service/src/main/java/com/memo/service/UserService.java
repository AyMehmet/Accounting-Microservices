package com.memo.service;







import com.memo.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findUserById(Long id);
    UserDto findByUsername(String username);
//    List<UserDto> getFilteredUsers() throws Exception;
    List<UserDto> getAllUsers() throws Exception;
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Long id);
    Boolean emailExist(UserDto userDto);

    boolean userIdExist(Long userId);
}
