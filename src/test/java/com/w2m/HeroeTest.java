package com.w2m;

import com.w2m.dto.HeroeDTO;
import com.w2m.dto.HeroesDTO;
import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.HeroeService;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroeTest {


    @Mock
    HeroeServiceImpl heroeService;

    @Autowired
    HeroeRepository heroeRepository;

    @InjectMocks
    ModelMapper modelMapper;

    HeroeDTO heroeBatmanDTO;
    HeroeDTO heroeSupermanDTO;
    HeroeDTO heroeWolverineDTO;

    HeroeDTO heroeSpidermanDTO;


    Heroe heroeBatmanModel;
    Heroe heroeSupermanModel;
    Heroe heroeWolverineModel;

    Heroe heroeSpidermanModel;

    Optional<HeroesDTO> heroes= Optional.of(HeroesDTO.builder().build());

    List<Heroe> heroesModel=new ArrayList<>();

    @Before
    public void setUpHeroes() {
        heroeBatmanDTO=HeroeDTO.builder().id(3252L).nombre("Batman").build();
        heroeSupermanDTO=HeroeDTO.builder().id(3255L).nombre("Superman").build();
        heroeWolverineDTO=HeroeDTO.builder().id(5325L).nombre("Wolverine").build();
        heroeSpidermanDTO=HeroeDTO.builder().id(5355L).nombre("Spiderman").build();

        heroeWolverineModel=Heroe.builder().id(5355L).nombre("Wolverine").build();

        heroeRepository=org.mockito.Mockito.mock(HeroeRepository.class);

        heroes.get().setHeroes(new ArrayList<>(List.of(heroeBatmanDTO, heroeSpidermanDTO, heroeWolverineDTO,heroeSpidermanDTO)));

        heroeBatmanModel=modelMapper.map(heroeBatmanDTO,Heroe.class);
        heroeSupermanModel=modelMapper.map(heroeSupermanDTO,Heroe.class);
        heroeWolverineModel=modelMapper.map(heroeWolverineDTO,Heroe.class);
        heroeSpidermanModel=modelMapper.map(heroeSpidermanDTO,Heroe.class);

        heroesModel.add(heroeBatmanModel);
        heroesModel.add(heroeWolverineModel);
        heroesModel.add(heroeSupermanModel);
        heroesModel.add(heroeSpidermanModel);

    }

    @Test
    public void whenConsultaHeroes() {
        when(heroeRepository.findAll()).thenReturn(heroesModel);
        when(heroeService.getAll()).thenReturn(heroes);
        HeroesDTO heroes= heroeService.getAll().get();
        assertEquals(true, !Objects.isNull(heroes));
    }

    @Test
    public void whenHeroeExiste(){
        when(heroeService.getHeroeById(9697L)).thenReturn(Optional.of(heroeWolverineDTO));
        when(heroeRepository.findById(9697L)).thenReturn(Optional.of(heroeWolverineModel));
        Optional<HeroeDTO> heroes= heroeService.getHeroeById(9697L);
        assertEquals(true, !heroes.isEmpty());
    }
    @Test
    public void whenHeroeNoExiste(){
        when(heroeRepository.findById(23596L)).thenReturn(Optional.empty());
        when(heroeService.getHeroeById(23596L)).thenReturn(any());
        Optional<HeroeDTO> heroe= heroeService.getHeroeById(23596L);
        assertEquals(null, heroe);

    }
}
