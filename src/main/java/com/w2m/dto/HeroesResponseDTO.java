package com.w2m.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroesResponseDTO {
    List<HeroeResponseDTO> heroes= new ArrayList<>();
}
