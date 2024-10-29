package com.memo.service;



import com.memo.dto.UserResponseWrapperDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",url = "http://localhost:9090")
public interface UserClientService {

    @GetMapping("/api/v1/user/{username}")
    UserResponseWrapperDto getUserDTOByUserName(@PathVariable("username") String username);
}
