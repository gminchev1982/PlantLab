package com.minchev.plantlab.services;

import com.minchev.plantlab.database.repositories.PlantRepository;
import com.minchev.plantlab.servicies.api.PlantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlantServiceIntegrationTest {

    @Autowired
    private PlantService service;

    @MockBean
    private PlantRepository mockRepository;


    @Test(expected = IllegalArgumentException.class)
    public void createPlant_whenNull_throw() {
        service.save(null);
        verify(mockRepository)
                .save(any());
    }

  /*  @Test
    public void createPlant_whenValid_plantCreated() {
        PlantServiceModel plant = new PlantServiceModel();


        when(mockRepository.save(any()))
                .thenReturn(new PlantEntity());

        service.save(plant);
        verify(mockRepository).save(any());
    }*/
}
