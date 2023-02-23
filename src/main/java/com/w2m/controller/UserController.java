package com.w2m.controller;

import com.w2m.dto.JwtResponse;
import com.w2m.dto.LoginRequest;
import com.w2m.dto.MessageResponse;
import com.w2m.dto.SignupRequest;
import com.w2m.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    private ResponseEntity<JwtResponse>login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){

        JwtResponse responseLogin=userService.login(loginRequest);
        return ResponseEntity.ok(responseLogin);

    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {

        ResponseEntity response=userService.registrarse(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("Usuario Registradoª"));

    }
}
