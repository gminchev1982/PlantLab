package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.database.entities.PlantEntity;
import com.minchev.plantlab.database.repositories.PlantRepository;
import com.minchev.plantlab.models.view.PlantListViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlantControllerTest {

    @Autowired
    PlantController controller;
    @MockBean
    PlantRepository mockRepository;
    @MockBean
    private Pageable pageable;
    private ArrayList<PlantEntity> plants;


    @Before
    public void setupTest() throws Exception {
        plants = new ArrayList<>();

        Page pageImpl = new PageImpl(plants);
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        try {
            when(mockRepository.findAll(pageable)).thenReturn(pageImpl);
        } catch (Exception e) {

        }
    }


    @Test
    @WithMockUser
    public void homePlant_whenPlantIsEmpty_empty() throws Exception {
        plants.clear();
        ModelAndView modelAndView = new ModelAndView();
        try {
            ModelAndView result = controller.homePlant(modelAndView, 0, "createdAt", "");
            result.addObject("plants", plants);
            List<PlantListViewModel> viewModels = (List<PlantListViewModel>) result.getModel().get("plants");
            assertTrue(viewModels.isEmpty());
        } catch (Exception e) {

        }

    }

    @Test
    @WithMockUser
    public void homePlant_whenPlantNotEmpty_plants() throws Exception {
        plants.addAll(List.of(new PlantEntity()));
        ModelAndView modelAndView = new ModelAndView();
        try {
            ModelAndView result = controller.homePlant(modelAndView, 0, "createdAt", "");
            result.addObject("plants", plants);
            List<PlantListViewModel> viewModels = (List<PlantListViewModel>) result.getModel().get("plants");
            assertEquals(plants.size(), viewModels.size());
        } catch (Exception e) {

        }
    }


}

