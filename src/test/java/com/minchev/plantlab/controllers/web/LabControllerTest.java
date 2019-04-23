/*package com.minchev.plantlab.controllers.web;

import com.minchev.plantlab.databases.entities.LabEntity;
import com.minchev.plantlab.databases.repositories.LabRepository;
import com.minchev.plantlab.models.view.LabListViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.any;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class LabControllerTest {
    @Autowired
    LabController controller;

    @MockBean
    LabRepository mockLabRepository;
    private ArrayList<LabEntity> labs;

    @Before
    public void setupTest() {
        labs = new ArrayList<>();

        when(mockLabRepository.findAll())
                .thenReturn(labs);
    }

    @Test
    @WithMockUser
    public void homeLabs_whenLabsHasEmpty_empty() {
        labs.clear();
        ModelAndView modelAndView = new ModelAndView();
        ModelAndView result = controller.homeLabs(modelAndView, 0, "");
        result.addObject("labs", labs);
        List<LabListViewModel> viewModels = (List<LabListViewModel>) result.getModel().get("labs");
        assertTrue(viewModels.isEmpty());
    }


    @Test
    @WithMockUser
    public void homeLabs_whenLabsHasNoEmpty_laps() {
        labs.addAll(List.of(new LabEntity()));
        ModelAndView modelAndView = new ModelAndView();
        ModelAndView result = controller.homeLabs(modelAndView, 0, "");
        result.addObject("labs", labs);
        List<LabListViewModel> viewModels = (List<LabListViewModel>) result.getModel().get("labs");
        assertEquals(labs.size(), viewModels.size());
    }

}*/
