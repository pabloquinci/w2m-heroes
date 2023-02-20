package com.w2m.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroeRequestDTO {

    @NonNull
    private String nombre;
}
