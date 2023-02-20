package com.w2m;

import com.w2m.dto.HeroeRequestDTO;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;
import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.HeroeYaExistenteException;
import com.w2m.exception.ResponseDefault;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroeTest {


    @Mock
    HeroeServiceImpl heroeService;

    @Autowired
    private HeroeRepository heroeRepository;

    @InjectMocks
    ModelMapper modelMapper;

    HeroeResponseDTO heroeBatmanDTO;
    HeroeResponseDTO heroeSupermanDTO;
    HeroeResponseDTO heroeWolverineDTO;

    HeroeResponseDTO heroeSpidermanDTO;

    HeroeResponseDTO heroeManolitoDTO;



    Heroe heroeBatmanModel;
    Heroe heroeSupermanModel;
    Heroe heroeWolverineModel;

    Heroe heroeSpidermanModel;

    Heroe heroeManolitoModel;



    Optional<HeroesResponseDTO> heroes= Optional.of(HeroesResponseDTO.builder().build());

    List<Heroe> heroesModel=new ArrayList<>();


    List<Heroe> heroesModelByNombre=new ArrayList<>();

    Optional<HeroesResponseDTO> heroesDTOByNombre= Optional.of(HeroesResponseDTO.builder().build());


    @Before
    public void setUpHeroes() {
        heroeRepository=org.mockito.Mockito.mock(HeroeRepository.class);

        heroeBatmanDTO= HeroeResponseDTO.builder().id(3466L).nombre("Batman").build();
        heroeSupermanDTO= HeroeResponseDTO.builder().id(5986L).nombre("Superman").build();
        heroeWolverineDTO= HeroeResponseDTO.builder().id(9697L).nombre("Wolverine").build();
        heroeSpidermanDTO= HeroeResponseDTO.builder().id(9398L).nombre("Spiderman").build();
        heroeManolitoDTO= HeroeResponseDTO.builder().id(5987L).nombre("Manolito el fuerte").build();


        //heroeWolverineModel=Heroe.builder().id(5355L).nombre("Wolverine").build();


        heroes.get().setHeroes(new ArrayList<>(List.of(heroeBatmanDTO, heroeSpidermanDTO, heroeWolverineDTO,heroeSpidermanDTO)));

        heroeBatmanModel=modelMapper.map(heroeBatmanDTO,Heroe.class);
        heroeSupermanModel=modelMapper.map(heroeSupermanDTO,Heroe.class);
        heroeWolverineModel=modelMapper.map(heroeWolverineDTO,Heroe.class);
        heroeSpidermanModel=modelMapper.map(heroeSpidermanDTO,Heroe.class);
        heroeManolitoModel=modelMapper.map(heroeManolitoDTO,Heroe.class);

        heroesModel.add(heroeBatmanModel);
        heroesModel.add(heroeWolverineModel);
        heroesModel.add(heroeSupermanModel);
        heroesModel.add(heroeSpidermanModel);
        heroesModel.add(heroeManolitoModel);


        heroesModelByNombre.add(heroeBatmanModel);
        heroesModelByNombre.add(heroeSupermanModel);
        heroesModelByNombre.add(heroeSpidermanModel);
        heroesModelByNombre.add(heroeManolitoModel);

        heroesDTOByNombre.get().setHeroes(new ArrayList<>(List.of(heroeBatmanDTO, heroeSpidermanDTO,heroeSpidermanDTO, heroeManolitoDTO)));




    }

    @Test
    public void whenConsultaHeroes() {
        when(heroeRepository.findAll()).thenReturn(heroesModel);
        when(heroeService.getAll()).thenReturn(heroes);
        HeroesResponseDTO heroes= heroeService.getAll().get();
        assertEquals(true, !Objects.isNull(heroes));
    }

    @Test
    public void whenHeroeExiste(){
        when(heroeService.getHeroeById(9697L)).thenReturn(Optional.of(heroeWolverineDTO));
        when(heroeRepository.findById(9697L)).thenReturn(Optional.of(heroeWolverineModel));
        Optional<HeroeResponseDTO> heroes= heroeService.getHeroeById(9697L);
        assertEquals(true, !heroes.isEmpty());
    }
    @Test
    public void whenHeroeNoExiste(){
        when(heroeRepository.findById(23596L)).thenReturn(Optional.empty());
        when(heroeService.getHeroeById(23596L)).thenReturn(any());
        Optional<HeroeResponseDTO> heroe= heroeService.getHeroeById(23596L);
        assertEquals(null, heroe);

    }

    @Test
    public void whenExistenHeroesConNombre(){
        when(heroeRepository.buscarPorNombre("man")).thenReturn(Optional.of(heroesModelByNombre));
        when(heroeService.getHeroesByNombre("man")).thenReturn(heroesDTOByNombre);
        Optional<HeroesResponseDTO> heroes= heroeService.getHeroesByNombre("man");
        assertEquals(4, heroes.get().getHeroes().size());
    }


    @Test
    public void whenSolicitudCreacionHeroeYaExiste(){

        when(heroeService.getHeroesByNombre("Batman")).thenThrow(new HeroeYaExistenteException(ResponseDefault.builder().build()));
        heroeService.crearHeroe(HeroeRequestDTO.builder().nombre("Batman").build());
    }
}
