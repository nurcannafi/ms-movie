package com.example.msmovie.controller;

import com.example.msmovie.model.dto.AuthenticationResponse;
import com.example.msmovie.model.request.SignInRequest;
import com.example.msmovie.model.request.SignUpRequest;
import com.example.msmovie.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        AuthenticationResponse response = authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        AuthenticationResponse response = authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(response);
    }
}
