package controller;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.w2m.AppW2mHeoresApplication;
import com.w2m.controller.HeroeController;
import com.w2m.dto.CrearHeroeRequestDTO;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;
import com.w2m.dto.OperacionHeroeResponseDTO;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.impl.HeroeServiceImpl;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AppW2mHeoresApplication.class)
@AutoConfigureMockMvc
public class HeroesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    HeroeServiceImpl heroeService;

    @MockBean
    HeroeRepository heroeRepository;

    @WithMockUser("spring")
    @Test
    public void whenGetAllOk()throws Exception {

        when(heroeService.getAll()).thenReturn(Optional.of(HeroesResponseDTO.builder().build()));
        mvc.perform(get("/heroes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetAllNoAuthorized()throws Exception {

        when(heroeService.getAll()).thenReturn(Optional.of(HeroesResponseDTO.builder().build()));
        mvc.perform(get("/heroes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser("spring")
    @Test
    public void whenGetByIdOk()throws Exception {

        when(heroeService.getHeroeById(any())).thenReturn(Optional.of(HeroeResponseDTO.builder().build()));
        mvc.perform(get("/heroes/getHeroeById")
                        .param("heroeId","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @WithMockUser("spring")
    @Test
    public void whenGetNombreIdOk()throws Exception {

        when(heroeService.getHeroesByNombre(any())).thenReturn(Optional.of(HeroesResponseDTO.builder().build()));
        mvc.perform(get("/heroes/getHeroesByNombre")
                        .param("nombre","Batman")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @WithMockUser("spring")
    @Test
    public void whenCrearHeroeOk()throws Exception {
        String jsonHeroeNuevo= "{\"nombre\":\"Batman2\"}";

        mvc.perform(post("/heroes/crearHeroe")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonHeroeNuevo))
                .andExpect(status().isOk());

    }


    /*
    @WithMockUser("spring")
    @Test
    public void whenModificarHeroeOk()throws Exception {
        when(heroeRepository.findByIdAndNombre(1L,"Batman")).thenReturn(Optional.of(Heroe.builder().build()));

        JSONObject json = new JSONObject();

        json.put("heroeId",1);
        json.put("nombre","Batman");

       String idHeroeNuevo= "{\"heroeId\":1";
        String nombreHeroeNuevo= "\"nombre\":\"name\"}";
        StringBuilder str= new StringBuilder(idHeroeNuevo);
        str.append(",");
        str.append(nombreHeroeNuevo);


        mvc.perform(post("/heroes/modificarHeroe")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                .andExpect(status().isOk());

    }*/

}
