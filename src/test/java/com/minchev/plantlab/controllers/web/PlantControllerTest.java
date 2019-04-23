
package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.databases.entities.PlantEntity;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.models.view.LabListViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class PlantControllerTest {

    @Autowired
   PlantController controller;

    @MockBean
    PlantRepository mockRepository;
    private ArrayList<PlantEntity> plants;

   @Before
    public void setupTest(){
        plants = new ArrayList<>();

     /*   when(mockRepository.findAll())
                .thenReturn(plants);
*/
    }


    @Test
    @WithMockUser
    public void homePlant_whenPlantIsEmpty_empty(){
        plants.clear();
        ModelAndView modelAndView = new ModelAndView();
        //ModelAndView result = controller.homePlant(modelAndView,0,"", "");
        // result.addObject("plantAll",plants);
        // List<LabListViewModel>  viewModels = (List<LabListViewModel>) result.getModel().get("plantAll");
        assertTrue(modelAndView.isEmpty());
    }










}

