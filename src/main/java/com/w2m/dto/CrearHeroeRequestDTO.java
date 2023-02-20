package com.w2m.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearHeroeRequestDTO {

    @NonNull
    private String nombre;
}
