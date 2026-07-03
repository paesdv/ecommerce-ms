package com.example.user_service.services;

import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.dto.ChangeEmailDTO;
import com.example.user_service.dto.ChangePassDTO;
import com.example.user_service.exceptions.EmailAlreadyInUseException;
import com.example.user_service.exceptions.InvalidCredentialsException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.models.User;
import com.example.user_service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserResponseDTO> findByEmail(String email){
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO);
    }

    public List<UserResponseDTO> findByName(String name) {
        return userRepository.findByNameContaining(name).stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public void changeEmail(Authentication auth, ChangeEmailDTO dto) {
        User user = getAuthenticatedUser(auth);
        validatePassword(dto.password(), user);

        if(userRepository.existsByEmail(dto.newEmail())){
            throw new EmailAlreadyInUseException("Email já existente!");
        }
        user.setEmail(dto.newEmail());
        userRepository.save(user);
    }

    public void changePassword(Authentication auth, ChangePassDTO dto) {
        User user = getAuthenticatedUser(auth);
        validatePassword(dto.currentPassword(), user);

        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
    }

    private void validatePassword(String rawPassword, User user){
        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            throw new InvalidCredentialsException("Senha incorreta.");
        }
    }

    private User getAuthenticatedUser(Authentication auth){
        return (User) auth.getPrincipal();
    }



}
