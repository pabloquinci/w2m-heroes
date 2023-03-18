package com.w2m.controller;

import com.w2m.dto.JwtResponse;
import com.w2m.dto.LoginRequest;
import com.w2m.dto.MessageResponse;
import com.w2m.dto.SignupRequest;
import com.w2m.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController {
    Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @Autowired
    UserService userService;
    @PostMapping("/login")
    private ResponseEntity<JwtResponse>login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){
        log.info("Login service");
        JwtResponse responseLogin=userService.login(loginRequest);
        return ResponseEntity.ok(responseLogin);

    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        log.info("Registro de nuevo usuario");
        ResponseEntity response=userService.registrarse(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("Usuario Registradoª"));

    }
}
