package com.minchev.plantlab.services;

import com.minchev.plantlab.components.PageIndex;
import com.minchev.plantlab.database.entities.ProductEntity;
import com.minchev.plantlab.database.repositories.ProductRepository;
import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;
import com.minchev.plantlab.servicies.api.ProductService;
import com.minchev.plantlab.validations.services.api.ProductValidationService;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository mockRepository;


    @MockBean
    ProductValidationService mockValidation;
    @Test
    public void save_whenNull_returnFalse() {

        when(mockValidation.isValidProductServiceModel(any())).thenReturn(false);
        Boolean result = service.save(any());
        assertFalse(result);
    }

/*    @Test
    public void saveProduct_whenValidModel_returnTrue() {
        when(mockValidation.isValidProductServiceModel(any())).thenReturn(true);
        when (mockRepository.save(any())).thenReturn(new ProductEntity());

        Boolean result = service.save(new ProductServiceModel());
        assertTrue(result);
    }*/

    @Test(expected = IllegalArgumentException.class)
    public void save_whenValidModel_returnException() {
        when(mockValidation.isValidProductServiceModel(any())).thenReturn(true);
        when (mockRepository.save(any()))
                .thenReturn(new ProductEntity());

        service.save(any());
        verify(mockRepository.save(any()));
    }

    @Test
    public void findAllProductActive_whenEmpty_returnEmpty(){
        when (this.mockRepository.findByActive(any())).thenReturn(Optional.empty());
        List<ProductLabViewModel> productActive = service.findAllProductActive();
        assertEquals(true, productActive.isEmpty());
    }
    @Test
    public void findAllProductActive_whenNotEmpty_returnListSize(){
        when(this.mockRepository.findByActive(any())).thenReturn(Optional.of(new ProductEntity()));

        List<ProductLabViewModel> productActive = service.findAllProductActive();
        assertEquals(1, productActive.size());
    }

    @Test(expected = ProductNotFoundException.class)
    public void findById_whenEmpty_returnNull() throws ProductNotFoundException {
        when (this.mockRepository.findById(any())).thenReturn(Optional.empty());
        ProductServiceModel productServiceModel = service.findById(any());
        assertEquals(null, productServiceModel);
    }

    @Test(expected = ProductNotFoundException.class)
    public void findById_whenEmpty_returnThrow() throws ProductNotFoundException {
        ProductServiceModel plantServiceModel = service.findById("");
    }

    @Test
    public void findById_whenHaveId_returnModel() throws ProductNotFoundException {
        ProductServiceModel plantServiceModelExpect = new ProductServiceModel(){{
            setId(null);
        }};
        when (this.mockRepository.findById(any())).thenReturn(Optional.of(new ProductEntity()));
        ProductServiceModel plantServiceModel = service.findById(any());
        assertEquals(plantServiceModelExpect.getId(), plantServiceModel.getId());
    }


    @Test
    public  void findAllProduct_whenEmpty_returnEmpty () {
        Page pageImpl = new PageImpl(new ArrayList<ProductEntity>());
        PageRequest pageable = PageRequest.of(0, PageIndex.PAGE_LIMIT);
        when (this.mockRepository.findAll(pageable)).thenReturn(pageImpl);
        List<ProductServiceModel> findAllProduct = service.findAllProduct(pageable);
        assertTrue(findAllProduct.isEmpty());
    }

    @Test
    public  void findAllProduct_whenListOfOne_returnListOfOne () {
        ArrayList<ProductEntity> plantEntities = new ArrayList<>();
        plantEntities.add(new ProductEntity());

        Page pageImpl = new PageImpl(plantEntities);
        PageRequest pageable = PageRequest.of(0,PageIndex.PAGE_LIMIT);
        when (this.mockRepository.findAll(pageable)).thenReturn(pageImpl);
        List<ProductServiceModel> findAllProduct = service.findAllProduct(pageable);
        assertEquals(1, findAllProduct.size());
    }


}
