package com.w2m.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModificarHeroeRequestDTO {
    private Long heroeId;
    private String nombre;
}
