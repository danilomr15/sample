package br.com.danilomr.postgres.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultErrorDTO {

    private String message;
}
