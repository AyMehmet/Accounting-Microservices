package com.memo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseWrapperDto {

    private boolean success;
    private String message;
    private Integer code;
    private UserDto data;

    public UserResponseWrapperDto(String message, Object data, HttpStatus httpStatus) {
        this.success = true;
        this.message = message;
        this.code = httpStatus.value();
        this.data = (UserDto) data;
    }
    public UserResponseWrapperDto(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = (UserDto) data;
    }

    public UserResponseWrapperDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = httpStatus.value();
        this.success = true;
    }
    public UserResponseWrapperDto(String message) {
        this.message = message;
        this.success = true;
    }

}
