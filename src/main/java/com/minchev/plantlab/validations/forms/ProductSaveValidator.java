package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.models.forms.ProductSaveForm;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.validation.Errors;

@Validator
public class ProductSaveValidator implements org.springframework.validation.Validator {
    private final ProductRepository productRepository;

    public ProductSaveValidator(ProductRepository productRepository) {
        this.productRepository=productRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductSaveForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductSaveForm productSaveForm = (ProductSaveForm) o;
        if (productSaveForm.getName()==null || productSaveForm.getName()=="" || productSaveForm.getName().isEmpty()) {

            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

        if ( !productSaveForm.getName().isEmpty() && (productSaveForm.getName().length() < 3 )) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH

            );
        }


        if (this.productRepository.findByName(productSaveForm.getName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.BARCODE_EXIST,
                    ValidationConstants.BARCODE_EXIST
            );
        }
    }
}
