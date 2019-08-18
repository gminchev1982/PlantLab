package com.minchev.plantlab.servicies.impl;

import com.minchev.plantlab.components.PageIndex;
import com.minchev.plantlab.components.SortPage;
import com.minchev.plantlab.database.entities.ProductEntity;
import com.minchev.plantlab.database.repositories.ProductRepository;
import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;
import com.minchev.plantlab.servicies.api.ProductService;
import com.minchev.plantlab.validations.services.api.ProductValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductValidationService productValidationService;
    private final PageIndex pageIndex;
    private final SortPage sortPage;
    private Pageable pagebleRequest;
    private List<Integer> pageNumbers = null;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductValidationService productValidationService,
                              PageIndex pageIndex,
                              SortPage sortPage,
                              ModelMapper modelMapper
    ) {
        this.productValidationService = productValidationService;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.pageIndex = pageIndex;
        this.sortPage = sortPage;
    }

    @Override
    public boolean save(ProductServiceModel productServiceModel) {

        if (productValidationService.isValidProductServiceModel(productServiceModel)) {
            ProductEntity product = this.modelMapper.map(productServiceModel, ProductEntity.class);
            this.productRepository.save(product);
            return true;
        } else return false;

    }

    @Override
    public List<ProductServiceModel> findAllProduct(PageRequest pageable) {
            Page<ProductEntity> records = this.productRepository.findAll(pageable);

        if(records!=null && records.hasContent()) {
            this.setPagingNumber(records.getTotalPages());
            return records.getContent()
                    .stream()
                    .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                    .collect(Collectors.toList())
                    ;
        }
        else return new ArrayList<ProductServiceModel>();
    }

    @Override
    public List<ProductLabViewModel> findAllProductActive() {
        return this.productRepository.findByActive(true)
                .stream()
                .map(u -> this.modelMapper.map(u, ProductLabViewModel.class))
                .collect(Collectors.toList());
    }

    private List<Integer> setPagingNumber(Integer totalPages) {

        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }

    @Override
    public PageRequest createPageRequest(Integer page, String sort) {
        this.pageIndex.setCurrentPage(page);
        this.sortPage.setSort(sort);
        return PageRequest.of(this.pageIndex.getCurrentPage(), PageIndex.PAGE_LIMIT, this.sortPage.getSortBy());
    }

    @Override
    public List<Integer> getPagingNumber() {
        return pageNumbers;
    }

    @Override
    public ProductServiceModel findById(String id) throws ProductNotFoundException {

        return this.productRepository.findById(id)
                .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                .orElseThrow(() -> new ProductNotFoundException());
    }

}
