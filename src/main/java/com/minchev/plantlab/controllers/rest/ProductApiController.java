package com.minchev.plantlab.controllers.rest;

import com.minchev.plantlab.models.forms.ProductEditForm;
import com.minchev.plantlab.models.forms.ProductSaveForm;
import com.minchev.plantlab.models.service.ProductServiceEditModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.servicies.ProductService;
import com.minchev.plantlab.validations.forms.ProductEditValidator;
import com.minchev.plantlab.validations.forms.ProductSaveValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.minchev.plantlab.validations.constants.ValidationConstants.PRODUCT_DONE;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {


    private final ProductSaveValidator productSaveValidator;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final ProductEditValidator productEditValidator;

    public ProductApiController(ProductSaveValidator productSaveValidator,
                                ProductEditValidator productEditValidator,
                                ProductService productService,
                                ModelMapper modelMapper) {

        this.productService = productService;
        this.productSaveValidator = productSaveValidator;
        this.productEditValidator = productEditValidator;
        this.modelMapper = modelMapper;

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity  save(@RequestBody ProductSaveForm productSaveForm, Errors errors) {

        this.productSaveValidator.validate(productSaveForm, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        ProductServiceModel produtServiceModel = this.modelMapper.map(productSaveForm, ProductServiceModel.class);
        this.productService.save(produtServiceModel);

        return ResponseEntity.status(HttpStatus.OK).body(PRODUCT_DONE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.modelMapper.map(this.productService.findById(id), ProductServiceModel.class));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody ProductEditForm productForm, Errors errors) {

        this.productEditValidator.validate(productForm, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        this.productService.edit(this.modelMapper.map(productForm, ProductServiceEditModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(PRODUCT_DONE);
    }
}
