package br.com.danilomr.postgres.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotNull(message = "Street cannot be null")
    private String street;

    @NotNull(message = "Number cannot be null")
    private Integer number;

    private String complement;

    @NotNull(message = "State cannot be null")
    private String state;

    @NotNull(message = "Country cannot be null")
    private String country;
}
