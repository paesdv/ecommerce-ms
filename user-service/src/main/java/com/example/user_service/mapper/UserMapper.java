package com.example.user_service.mapper;

import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

    public User toEntity(UserRequestDTO dto){
        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();

        return user;
    }


}
