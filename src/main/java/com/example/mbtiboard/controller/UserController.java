package com.example.mbtiboard.controller;


import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public MsgResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public MsgResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    @PostMapping("/findId")
    public String findId(@RequestBody FindIdDto findIdDto) {
        return userService.findId(findIdDto);
    }

//    @GetMapping ("/findPw")
//    public String findPw(@RequestBody FindPwDto findPwDto) {
//        return userService.findPw(findPwDto);
//    }
//
//    @PutMapping("/rePw")
//    public MsgResponseDto rePw(@RequestBody String rePw ) {
//        return userService rePw()
//    }
}