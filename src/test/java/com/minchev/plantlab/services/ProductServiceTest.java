package com.minchev.plantlab.services;

import com.minchev.plantlab.databases.entities.ProductEntity;
import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.servicies.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository mockProductRepository;


    @Test(expected = IllegalArgumentException.class)
    public void createProduct_whenNull_throw() {
        service.save(null);
        verify(mockProductRepository)
                .save(any());
    }


   /*
   @Test
   public void createProduct_whenValid_productCreated() {
        ProductServiceModel product = new ProductServiceModel();

        when(mockProductRepository.save(any()))
                .thenReturn(new ProductEntity());

        service.save(product);
        verify(mockProductRepository)
                .save(any());
    }*/



}
