package com.w2m.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EliminarHeroeRequestDTO {
    @NonNull
    private Long heroeId;
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
}
