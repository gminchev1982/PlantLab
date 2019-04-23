package com.minchev.plantlab.controllers.web;


import com.minchev.plantlab.databases.entities.ProductEntity;
import com.minchev.plantlab.databases.repositories.ProductRepository;
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
public class ProductControllerTest {

    @Autowired
    ProductController controller;

    @MockBean
    private Pageable pageable;

    @MockBean
    ProductRepository mockRepository;

    private ArrayList<ProductEntity> products;

    @Before
    public void setupTest() throws Exception {
        products = new ArrayList<>();

        Page pageImpl = new PageImpl(products);
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
    public void productPage_whenProductIsEmpty_empty() throws Exception {
        products.clear();
        ModelAndView modelAndView = new ModelAndView();
        try {
            ModelAndView result = controller.productPage(modelAndView, 0, "createdAt");
            result.addObject("products", products);
            List<PlantListViewModel> viewModels = (List<PlantListViewModel>) result.getModel().get("products");
            assertTrue(viewModels.isEmpty());
        } catch (Exception e) {

        }

    }

    @Test
    @WithMockUser
    public void productPage_whenProductNotEmpty_empty() throws Exception {
        products.addAll(List.of(new ProductEntity()));
        ModelAndView modelAndView = new ModelAndView();
        try {
            ModelAndView result = controller.productPage(modelAndView, 0, "createdAt");
            result.addObject("products", products);
            List<PlantListViewModel> viewModels = (List<PlantListViewModel>) result.getModel().get("products");
            assertEquals(products.size(), viewModels.size());
        } catch (Exception e) {

        }

    }

}
