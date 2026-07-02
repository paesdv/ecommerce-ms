package com.example.user_service.services;

import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.dto.changeEmailDTO;
import com.example.user_service.dto.changePassDTO;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.models.User;
import com.example.user_service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado."));
        return userMapper.toDTO(user);
    }

    public List<UserResponseDTO> findByName(String name) {
        return userRepository.findByNameContaining(name).stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public void changeEmail(Authentication auth, changeEmailDTO dto) {
        User user = (User) auth.getPrincipal();
        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new RuntimeException("Senha incorreta.");
        }
        user.setEmail(dto.newEmail());
        userRepository.save(user);
    }

    public void changePassword(Authentication auth, changePassDTO dto) {
        User user = (User) auth.getPrincipal();
        if(!passwordEncoder.matches(dto.currentPassword(), user.getPassword())){
            throw new RuntimeException("Senha incorreta.");
        }
        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
    }



}
