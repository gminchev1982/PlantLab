package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.models.forms.ProductEditForm;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.validation.Errors;

@Validator
public class ProductEditValidator implements org.springframework.validation.Validator {


    private final ProductRepository productRepository;

    public ProductEditValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductEditForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductEditForm productEditForm = (ProductEditForm) o;
        if (productEditForm.getName() == null || productEditForm.getName() == "" || productEditForm.getName().isEmpty()) {

            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

        if (!productEditForm.getName().isEmpty() && (productEditForm.getName().length() < 3)) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH

            );
        }


        if (!productEditForm.getNameold().contentEquals(productEditForm.getName()) && this.productRepository.findByName(productEditForm.getName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.BARCODE_EXIST,
                    ValidationConstants.BARCODE_EXIST
            );
        }
    }
}

