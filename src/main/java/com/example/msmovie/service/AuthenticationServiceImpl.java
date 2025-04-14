package com.example.msmovie.service;

import com.example.msmovie.domain.entity.UserEntity;
import com.example.msmovie.domain.repository.UserRepository;
import com.example.msmovie.exception.UserAlreadyExistsException;
import com.example.msmovie.model.dto.AuthenticationResponse;
import com.example.msmovie.model.request.SignInRequest;
import com.example.msmovie.model.request.SignUpRequest;
import com.example.msmovie.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse signUp(SignUpRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(userEntity);
        String token = jwtService.generateToken(userEntity);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        String token = jwtService.generateToken(userEntity);
        return new AuthenticationResponse(token);
    }
}
