package com.example.msmovie.service;

import com.example.msmovie.model.dto.AuthenticationResponse;
import com.example.msmovie.model.request.SignInRequest;
import com.example.msmovie.model.request.SignUpRequest;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest request);

    AuthenticationResponse signIn(SignInRequest request);
}
