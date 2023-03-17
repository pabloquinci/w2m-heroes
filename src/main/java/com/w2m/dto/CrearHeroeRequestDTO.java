package com.w2m.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearHeroeRequestDTO {

    @NotBlank(message = "El parametro nombre no puede ser null")
    private String nombre;
}
