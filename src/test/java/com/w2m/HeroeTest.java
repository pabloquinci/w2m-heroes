package com.w2m;

import com.w2m.dto.HeroeDTO;
import com.w2m.dto.HeroesDTO;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")

public class HeroeTest {


    @Mock
    private HeroeServiceImpl heroeService;

    @InjectMocks
    ModelMapper modelMapper;

    HeroeDTO heroeBatman;

    Optional<HeroesDTO> heroes= Optional.of(HeroesDTO.builder().build());

    @Before
    public void setUpHeroes() {
        heroeBatman=HeroeDTO.builder().id(1L).nombre("Batman").build();
        heroes.get().setHeroes(new ArrayList<>(List.of(heroeBatman)));

    }

    @Test
    public void whenConsultaHeroes() {
        when(heroeService.getAll()).thenReturn(Optional.of(heroes.get()));
        Optional<HeroesDTO> heroes= heroeService.getAll();
        assertEquals(true, !heroes.isEmpty());
    }
}
