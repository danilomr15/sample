package br.com.danilomr.postgres.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Username cannot be null")
    private String username;

    private List<AddressDTO> addresses;
}
