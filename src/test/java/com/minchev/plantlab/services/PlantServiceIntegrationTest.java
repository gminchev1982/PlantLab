package com.minchev.plantlab.services;

import com.minchev.plantlab.components.PageIndex;
import com.minchev.plantlab.database.entities.PlantEntity;
import com.minchev.plantlab.database.repositories.PlantRepository;
import com.minchev.plantlab.errors.PlantNotFoundException;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.view.PlantLabViewModel;
import com.minchev.plantlab.models.view.PlantListViewModel;
import com.minchev.plantlab.servicies.api.PlantService;
import com.minchev.plantlab.validations.services.api.PlantValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
 public class PlantServiceIntegrationTest {

    @Autowired
    private PlantService service;

    @MockBean
    private PlantRepository mockRepository;

    @MockBean
    PlantValidationService mockPlantValidation;
    @Test
    public void save_whenNull_returnFalse() {

        when(mockPlantValidation.isValidPlantServiceModel(any())).thenReturn(false);
        Boolean result = service.save(any());
        assertFalse(result);
    }

    @Test
    public void save_whenValidModel_returnTrue() {
        when(mockPlantValidation.isValidPlantServiceModel(any())).thenReturn(true);
        when (mockRepository.save(any()))
                .thenReturn(new PlantEntity());

        Boolean result = service.save(new PlantServiceModel());
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void save_whenValidModel_returnException() {
        when(mockPlantValidation.isValidPlantServiceModel(any())).thenReturn(true);
        when (mockRepository.save(any()))
                .thenReturn(new PlantEntity());

        service.save(any());
        verify(mockRepository.save(any()));
    }

    @Test
    public void findAllPlantsActive_whenEmpty_returnEmpty(){
        when (this.mockRepository.findByActive(any())).thenReturn(Optional.empty());
        List<PlantLabViewModel> plantsActive = service.findAllPlantsActive();
        assertEquals(true, plantsActive.isEmpty());
    }
    @Test
    public void findAllPlantsActive_whenNotEmpty_returnListSize(){
        when(this.mockRepository.findByActive(any())).thenReturn(Optional.of(new PlantEntity()));

        List<PlantLabViewModel> plantsActive = service.findAllPlantsActive();
        assertEquals(1, plantsActive.size());
    }

    @Test(expected = PlantNotFoundException.class)
    public void findPlantById_whenEmpty_returnNull() throws PlantNotFoundException {
        when (this.mockRepository.findById(any())).thenReturn(Optional.empty());
        PlantServiceModel plantServiceModel = service.findPlantById(any());
        assertEquals(null, plantServiceModel);
    }

    @Test(expected = PlantNotFoundException.class)
    public void findPlantById_whenEmpty_returnThrow() throws PlantNotFoundException {
        PlantServiceModel plantServiceModel = service.findPlantById("");
    }

    @Test
    public void findPlantById_whenHaveId_returnModel() throws PlantNotFoundException {
        PlantServiceModel plantServiceModelExpect = new PlantServiceModel(){{
            setId(null);
        }};
       when (this.mockRepository.findById(any())).thenReturn(Optional.of(new PlantEntity()));
       PlantServiceModel plantServiceModel = service.findPlantById(any());
        assertEquals(plantServiceModelExpect.getId(), plantServiceModel.getId());
    }

    @Test
    public  void findAllPlants_whenEmpty_returnEmpty () {
        Page pageImpl = new PageImpl(new ArrayList<PlantEntity>());
        PageRequest pageable = PageRequest.of(0,PageIndex.PAGE_LIMIT);
        when (this.mockRepository.findAll(pageable)).thenReturn(pageImpl);
        List<PlantListViewModel> findAllPlant = service.findAllPlants(pageable);
        assertTrue(findAllPlant.isEmpty());
    }

    @Test
    public  void findAllPlants_whenListOfOne_returnListOfOne () {
        ArrayList<PlantEntity> plantEntities = new ArrayList<>();
        plantEntities.add(new PlantEntity());

        Page pageImpl = new PageImpl(plantEntities);
        PageRequest pageable = PageRequest.of(0,PageIndex.PAGE_LIMIT);
        when (this.mockRepository.findAll(pageable)).thenReturn(pageImpl);
        List<PlantListViewModel> findAllPlant = service.findAllPlants(pageable);
        assertEquals(1, findAllPlant.size());
    }




}
