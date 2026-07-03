package com.example.user_service.services;

import com.example.user_service.config.TokenProvider;
import com.example.user_service.dto.AuthDTO;
import com.example.user_service.dto.TokenResponseDTO;
import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.enums.Role;
import com.example.user_service.exceptions.EmailAlreadyInUseException;
import com.example.user_service.models.User;
import com.example.user_service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private long expirationTime;


    public TokenResponseDTO login(AuthDTO dto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        String token = tokenProvider.gerarToken(authentication);
        return new TokenResponseDTO(token, expirationTime);
    }

    public void register(UserRequestDTO dto){
        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new EmailAlreadyInUseException("Email já cadastrado");
        }

        User user = User.builder()
                .name(dto.name())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .role(Role.USER)
                .build();

        userRepository.save(user);

    }


}
