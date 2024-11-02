package com.memo.controller;

import com.memo.dto.ResponseWrapper;
import com.memo.dto.UserDto;
import com.memo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @Operation(summary = " User list retrived")
//    @ApiResponse(responseCode = "200")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('Root User','Admin')")
    public ResponseEntity<ResponseWrapper> list() throws Exception {
        List<UserDto> userDtoList=userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Version","1")
                .body(new ResponseWrapper("Users are retrieved succesfully",userDtoList,HttpStatus.OK));
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('Root User','Admin')")
    public ResponseEntity<ResponseWrapper> create( @RequestBody @Valid UserDto userDto, BindingResult result) {
        boolean emailExist = userService.emailExist(userDto);
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper(result.getFieldError().toString()));
        } else if (emailExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("A user with this email already exists. Please try with different email."));
        } else {
            userService.save(userDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header("Version", "1")
                    .body(new ResponseWrapper("User is created succesfully"));
        }
    }


    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAnyAuthority('Root User','Admin')")
    public ResponseEntity<ResponseWrapper> update(@PathVariable("userId") Long userId, @Valid @RequestBody UserDto userDto, BindingResult result) {

        boolean userIdExist = userService.userIdExist(userId);
        if (userIdExist) {
        userDto.setId(userId);  // spring cannot set id since it is not seen in UI and we need to check if updated email is used by different user.
        boolean emailExist = userService.emailExist(userDto);
            if (result.hasErrors() ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper(result.getFieldError().toString()));
            } else  {
                if (emailExist) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("A user with this email already exists. Please try with different email."));
                } else {
                    userService.update(userDto);
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("User is updated succesfully"));
                }
            }
        } else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("A user is not available. Please try with different userid."));
    }
    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('Root User','Admin')")
    public ResponseEntity<ResponseWrapper> delete(@PathVariable("userId") Long userId) {
        boolean userIdExist = userService.userIdExist(userId);
        if (userIdExist) {
            userService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("User is deleted."));
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("A user is not available. Please try with different userid."));
    }
//    @ModelAttribute
//    public void commonAttributes(Model model){
//        model.addAttribute("companies", companyService.getAllCompanies());
//        model.addAttribute("userRoles", roleService.getFilteredRolesForCurrentUser());
//        model.addAttribute("title", "Cydeo Accounting-User");
//    }

}
