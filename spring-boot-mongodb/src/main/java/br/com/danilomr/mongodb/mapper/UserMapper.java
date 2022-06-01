package br.com.danilomr.mongodb.mapper;

import br.com.danilomr.mongodb.controller.dto.UserDTO;
import br.com.danilomr.mongodb.document.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User map(final UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }

    public static UserDTO map(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static List<UserDTO> map(final List<User> users) {
        return users.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }
}
