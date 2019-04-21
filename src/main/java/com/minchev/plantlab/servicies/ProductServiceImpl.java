package com.minchev.plantlab.servicies;

import com.minchev.plantlab.databases.entities.ProductEntity;
import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.errors.ProductNotFoundException;
import com.minchev.plantlab.models.service.ProductServiceEditModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.models.view.ProductLabViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl  implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private Pageable pagebleRequest;
    private List<Integer> pageNumbers = null;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public ProductServiceModel save(ProductServiceModel productServiceModel) {

        ProductEntity product = this.modelMapper.map(productServiceModel, ProductEntity.class);
        return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);

    }

    @Override
    public boolean edit(ProductServiceEditModel productEditForm) {
        ProductEntity product = this.productRepository.findById(productEditForm.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        product.setActive(productEditForm.isActive());
        product.setName(productEditForm.getName());
        this.productRepository.save(product);


        return true;
    }

    @Override
    public List<ProductServiceModel> findAllProduct(Integer page, String sort) {

        Integer limit = 2;
        if (page-1<0) page =0; else page= page-1;
        String[]  sortType = sort.split(",");
        Sort sortBy = null;
        if (sortType.length>0 && sortType[1].equals("asc")){
            sortBy= new Sort(Sort.Direction.ASC, sortType[0]);
        }
        if (sortType.length>0 && sortType[1].equals("desc")){
            sortBy= new Sort(Sort.Direction.DESC, sortType[0]);
        }
        PageRequest pageable = PageRequest.of(page, limit, sortBy);
        Page<ProductEntity> records =   this.productRepository.findAll(pageable);

        this.setPagingNumber(records.getTotalPages());

        return records.getContent()
                .stream()
                .map(u -> this.modelMapper.map(u, ProductServiceModel.class))
                .collect(Collectors.toList())
                ;
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
