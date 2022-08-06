package com.library.library.controller;

import com.library.library.controller.dto.UserDto;
import com.library.library.controller.validation.EmailValid;
import com.library.library.controller.validation.IsEmailUser;
import com.library.library.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userService.pageUsers(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{email}")
    public UserDto getUser(@PathVariable @EmailValid @IsEmailUser String email) {
        return userService.getUser(email);
    }
}