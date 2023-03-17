package controller;


import com.w2m.AppW2mHeoresApplication;
import com.w2m.dto.HeroeResponseDTO;
import com.w2m.dto.HeroesResponseDTO;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.impl.HeroeServiceImpl;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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



    @WithMockUser("spring")
    @Test
    public void whenModificarHeroeOk()throws Exception {
        lenient().when(heroeRepository.findById(3466L)).thenReturn(Optional.of(Heroe.builder().build()));

        JSONObject json = new JSONObject();

        json.put("heroeId",1);
        json.put("nombre","Batman");

       String idHeroeNuevo= "{\"heroeId\":3466";
        String nombreHeroeNuevo= "\"nombre\":\"Batman\"}";
        StringBuilder str= new StringBuilder(idHeroeNuevo);
        str.append(",");
        str.append(nombreHeroeNuevo);


        mvc.perform(put("/heroes/modificarHeroe")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str.toString()))
                .andExpect(status().isOk());

    }

    @WithMockUser("spring")
    @Test
    public void whenEliminarHeroeOk()throws Exception {
        lenient().when(heroeRepository.findById(3466L)).thenReturn(Optional.empty());

        JSONObject json = new JSONObject();

        json.put("heroeId",1);

        String idHeroeNuevo= "{\"heroeId\":3466";
        String nombreHeroeNuevo= "\"nombre\":\"Batman\"}";
        StringBuilder str= new StringBuilder(idHeroeNuevo);
        str.append(",");
        str.append(nombreHeroeNuevo);

        mvc.perform(delete("/heroes/eliminarHeroe")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str.toString()))
                .andExpect(status().isOk());

    }

}
