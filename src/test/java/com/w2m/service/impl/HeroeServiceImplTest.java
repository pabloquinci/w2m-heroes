package com.w2m.service.impl;

import com.w2m.dto.*;
import com.w2m.exception.HeroeNoEncontradoException;
import com.w2m.exception.HeroeYaExistenteException;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("Test Heroes")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

public class HeroeServiceImplTest {


   HeroeServiceImpl heroeService;


    ModelMapper modelMapper=new ModelMapper();

    @MockBean
    private HeroeRepository heroeRepository;

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


        heroes.get().setHeroes(new ArrayList<>(List.of(heroeBatmanDTO, heroeSpidermanDTO, heroeWolverineDTO,heroeSpidermanDTO)));

        heroeBatmanModel=modelMapper.map(heroeBatmanDTO,Heroe.class);
        heroeSupermanModel=modelMapper.map(heroeSupermanDTO,Heroe.class);
        heroeWolverineModel=modelMapper.map(heroeWolverineDTO,Heroe.class);
        heroeSpidermanModel=modelMapper.map(heroeSpidermanDTO,Heroe.class);
        heroeManolitoModel=modelMapper.map(heroeManolitoDTO,Heroe.class);

        heroeWolverineModel=Heroe.builder()
                .id(heroeWolverineDTO.getId())
                .nombre(heroeWolverineDTO.getNombre())
                .build();

        heroesModel.add(heroeBatmanModel);
        heroesModel.add(heroeWolverineModel);
        heroesModel.add(heroeSupermanModel);
        heroesModel.add(heroeSpidermanModel);
        heroesModel.add(heroeManolitoModel);


        heroesModelByNombre.add(heroeBatmanModel);
        heroesModelByNombre.add(heroeSupermanModel);
        heroesModelByNombre.add(heroeSpidermanModel);
        heroesModelByNombre.add(heroeManolitoModel);

        modelMapper=mock(ModelMapper.class);

        heroeService= new HeroeServiceImpl(heroeRepository, modelMapper);

        heroesDTOByNombre.get().setHeroes(new ArrayList<>(List.of(heroeBatmanDTO, heroeSpidermanDTO,heroeSpidermanDTO, heroeManolitoDTO)));

    }

    @DisplayName("Se testea el servicio que devuelve todos los heroes")
    @Test
    public void whenConsultaHeroes() {
        ReflectionTestUtils.setField(heroeService,"modelMapper",modelMapper);
        lenient().when(heroeRepository.findAll()).thenReturn(heroesModel);
        //lenient().when(heroeService.getAll()).thenReturn(heroes);
        HeroesResponseDTO heroes= heroeService.getAll().get();
        assertTrue(heroeService.getAll().isPresent());
    }

    @DisplayName("Se testea heroe existente en la base")
    @Test
    public void whenHeroeExiste(){
        ReflectionTestUtils.setField(heroeService,"modelMapper",modelMapper);
        lenient().when(heroeRepository.findById(9697L)).thenReturn(Optional.of(heroeWolverineModel));
       // lenient().when(heroeService.getHeroeById(9697L)).thenReturn(Optional.of(heroeWolverineDTO));
        Optional<HeroeResponseDTO> heroes= heroeService.getHeroeById(9697L);
        assertEquals(true, !heroes.isEmpty());
    }


    @DisplayName("Se testea heroe inexistente")
    @Test(expected = HeroeNoEncontradoException.class)
    public void whenHeroeNoExiste(){
        lenient().when(heroeRepository.findById(23596L)).thenReturn(Optional.empty());
        //lenient().when(heroeService.getHeroeById(23596L)).thenReturn(any());
        Optional<HeroeResponseDTO> heroe= heroeService.getHeroeById(23596L);
       // assertEquals(null, heroe);

    }

    @DisplayName("Se testea si existe un heroe que coincida con el patron asado por parametro")
    @Test
    public void whenExistenHeroesConNombre(){
        lenient().when(heroeRepository.buscarPorNombre("man")).thenReturn(Optional.of(heroesModelByNombre));
        //lenient().when(heroeService.getHeroesByNombre("man")).thenReturn(heroesDTOByNombre);
        Optional<HeroesResponseDTO> heroes= heroeService.getHeroesByNombre("man");
        assertEquals(4, heroes.get().getHeroes().size());
    }


    @DisplayName("Se testea el servicio de creacion de Heroe con un heroe ya existente")
    @Test(expected = HeroeYaExistenteException.class)
    public void whenSolicitudCreacionHeroeYaExiste(){

        lenient().when(heroeRepository.findByNombre("Batman")).thenReturn(Optional.of(heroeBatmanModel));
        heroeService.crearHeroe(CrearHeroeRequestDTO.builder().nombre("Batman").build());
    }

    @DisplayName("Se testea el servicio modifica un heroe, con un heroe inexistente")
    @Test(expected = HeroeNoEncontradoException.class)
    public void whenSOlicituModificacionHeroeNoExistente(){
        lenient().when(heroeRepository.findById(34466L)).thenReturn(Optional.empty());
        heroeService.modificarHeore(ModificarHeroeRequestDTO.builder().heroeId(34466L).nombre("LALALALA").build());
    }

    @DisplayName("Se testea el servicio que elimina un heroe, con heroe existente")
    @Test
    public void whenEliminarHeroeExiste(){
        lenient().when(heroeRepository.findById(9697L)).thenReturn(Optional.of(heroeWolverineModel));
        heroeService.modificarHeore(ModificarHeroeRequestDTO.builder().heroeId(9697L).nombre("LALALALA").build());
    }


    @DisplayName("Se testea el servicio que elimina un heroe, con heroe existente")
    @Test
    public void whenEliminarHeroe(){
        Optional <Heroe> heroeBuscado=Optional.of(heroeBatmanModel);
        lenient().when(heroeRepository.findByIdAndNombre(3466L,"Batman")).thenReturn(heroeBuscado);
        heroeService.eliminarHeroe(EliminarHeroeRequestDTO.builder().heroeId(3466L).nombre("Batman").build());
    }

    @DisplayName("Se testea el servicio que elimina un heroe, con heroe inexistente")
    @Test(expected = HeroeNoEncontradoException.class)
    public void whenEliminarHeroeConHeroeInexistente(){
        lenient().when(heroeRepository.findByIdAndNombre(34366L,"Batman")).thenReturn(Optional.empty());
        heroeService.eliminarHeroe(EliminarHeroeRequestDTO.builder().heroeId(3466L).nombre("Batman").build());
    }

}
