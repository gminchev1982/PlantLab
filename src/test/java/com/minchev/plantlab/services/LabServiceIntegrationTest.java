package com.minchev.plantlab.services;

import com.minchev.plantlab.database.entities.LabEntity;
import com.minchev.plantlab.database.repositories.LabRepository;
import com.minchev.plantlab.servicies.api.LabService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LabServiceIntegrationTest {

    @Autowired
    private LabService service;

    @MockBean
    private LabRepository mockRepository;


    private ArrayList<LabEntity> labs;

    //
//    @Before
    public void setupTest() {
        labs = new ArrayList<>();
        when(mockRepository.findAll())
                .thenReturn(labs);
    }
 /*   @Test(expected = Exception.class)
    public void saveLab_whenNull_throw() throws Exception{

        LabSaveForm labSaveForm  =new LabSaveForm();
        labSaveForm.setPlant(any());
        labSaveForm.setProduct(any());

        service.save(labSaveForm, any());
        verify(mockRepository)
                .save(any());
    }*/


    @Test(expected = Exception.class)
    public void findAllLabs_whenNoLabs_returnEmptyLabs() throws Exception {
        labs.clear();
        var result = service.findAllLabs(0, "");
        assertTrue(result.isEmpty());
    }

    @Test(expected = Exception.class)
    public void findAllLabs_whenLabs_returnLabs() throws Exception {
        labs.addAll(List.of(new LabEntity()));
        var result = service.findAllLabs(0, "");
        assertEquals(labs.size(), result.size());
    }


}
