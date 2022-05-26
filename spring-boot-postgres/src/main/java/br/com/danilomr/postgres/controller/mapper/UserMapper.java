package br.com.danilomr.postgres.controller.mapper;

import br.com.danilomr.postgres.controller.dto.UserDTO;
import br.com.danilomr.postgres.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {

    public static List<UserDTO> toDTO(final List<User> users) {

        return Optional.ofNullable(users)
                .map(list -> list.stream()
                        .map(UserMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    public static UserDTO toDTO(final User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .addresses(AddressMapper.toDTO(user.getAddresses()))
                .build();
    }

    public static User toEntity(final UserDTO userDTO) {
        final User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .addresses(AddressMapper.toEntity(userDTO.getAddresses()))
                .build();

        user.getAddresses().forEach(address -> address.setUser(user));
        return user;
    }
}
