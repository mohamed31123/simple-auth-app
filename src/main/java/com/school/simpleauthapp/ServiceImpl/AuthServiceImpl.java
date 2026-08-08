package com.school.simpleauthapp.ServiceImpl;

import com.school.simpleauthapp.dto.request.LoginRequest;
import com.school.simpleauthapp.dto.request.RegisterRequest;
import com.school.simpleauthapp.dto.response.AuthResponse;
import com.school.simpleauthapp.entity.User;
import com.school.simpleauthapp.repository.UserRepository;
import com.school.simpleauthapp.service.AuthService;
import com.school.simpleauthapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.username());
        user.setPassword(
                passwordEncoder.encode(request.password())
        );
        user.setRole("USER");

        userRepository.save(user);

        String token =
                jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        String token =
                jwtService.generateToken(
                        request.username()
                );

        return new AuthResponse(token);
    }
}
