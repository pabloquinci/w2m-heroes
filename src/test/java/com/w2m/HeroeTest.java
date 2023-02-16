package com.w2m;

import com.w2m.model.Heroe;
import com.w2m.persistence.HeroeRepository;
import com.w2m.service.HeroeService;
import com.w2m.service.impl.HeroeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")

public class HeroeTest {

    @Mock
    HeroeServiceImpl heroesService;

    @Mock
    HeroeRepository heroeRepository;


    @Before
    public void setUpHeroes() {

    }

    @Test
    public void whenConsultaHeroes() {
        Optional<List<Heroe>> heroes= Optional.of(heroeRepository.findAll());
        assertEquals(false, !heroes.isEmpty());
    }
}
