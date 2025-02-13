package com.vangogh.hotel.infrastructure.config.security.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailServiceImpl userDetailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public Map<String, String> nombre(@PathVariable String username) {
        String nombre = userDetailService.getNombre(username);
        Map<String, String> response = new HashMap<>();
        response.put("nombre", nombre);
        return response;
    }
}
