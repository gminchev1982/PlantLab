package com.minchev.plantlab.servicies;

import com.minchev.plantlab.components.PageIndex;
import com.minchev.plantlab.components.SortPage;
import com.minchev.plantlab.databases.entities.ProductEntity;
import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceEditModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;
import com.minchev.plantlab.validations.services.ProductValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl  implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductValidationService productValidationService;
    private Pageable pagebleRequest;
    private List<Integer> pageNumbers = null;
    private final PageIndex pageIndex;
    private final SortPage sortPage;

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

         if (productValidationService.isValid(productServiceModel)) {

             try {
             ProductEntity product = this.modelMapper.map(productServiceModel, ProductEntity.class);
             this.productRepository.saveAndFlush(product);
             return true;
             } catch (Exception e) {
                 e.printStackTrace();
                 return false;
             }
         }  else return  false;

    }

    @Override
    public boolean edit(ProductServiceEditModel productEditForm) {
        ProductServiceModel productServiceModel = this.modelMapper.map(productEditForm, ProductServiceModel.class);

        if (productValidationService.isValid(productServiceModel)) {
            ProductEntity product = this.productRepository.findById(productServiceModel.getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found!"));

            try {
                ProductEntity productEntity = this.modelMapper.map(productServiceModel, ProductEntity.class);
                this.productRepository.save(productEntity);
                return true;
            } catch (Exception e) {
                return false;
            }

        } else return false;
    }

    @Override
    public List<ProductServiceModel> findAllProduct(Integer page, String sort) {

        this.pageIndex.setCurrentPage(page);
        this.sortPage.setSort(sort);

        try {
        PageRequest pageable = PageRequest.of(this.pageIndex.getCurrentPage(), this.pageIndex.PAGE_LIMIT, this.sortPage.getSortBy());
        Page<ProductEntity> records =   this.productRepository.findAll(pageable);

        this.setPagingNumber(records.getTotalPages());

        return records.getContent()
                .stream()
                .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                .collect(Collectors.toList())
                ;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductLabViewModel> findAllProductActive() {
        return this.productRepository.findAll()
                .stream()
                .filter(f->f.isActive()==true)
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
    public List<Integer> getPagingNumber() {
        return pageNumbers;
    }

    @Override
    public ProductServiceModel findById(String id) {

        return this.productRepository.findById(id)
                .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

}
