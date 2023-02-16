package com.w2m;

import com.w2m.dto.HeroeDTO;
import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.HeroeService;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

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

    Heroe heroe1;
    List<Heroe> heroes= new ArrayList<>();

    @Before
    public void setUpHeroes() {
        heroe1=Heroe.builder().id(1L).nombre("Pablo").build();
        heroes.add(heroe1);

    }

    @Test
    public void whenConsultaHeroes() {
        when(heroeService.getAll()).thenReturn(Optional.of(heroes));
        Optional<List<Heroe>> heroes= heroeService.getAll();
        assertEquals(true, !heroes.isEmpty());
    }
}
