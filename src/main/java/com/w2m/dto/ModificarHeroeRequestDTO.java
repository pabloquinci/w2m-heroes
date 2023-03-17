package com.w2m.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModificarHeroeRequestDTO {
    @NotNull
    private Long heroeId;
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
}
