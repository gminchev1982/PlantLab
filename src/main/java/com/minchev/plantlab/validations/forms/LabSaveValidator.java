package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.databases.repositories.LabRepository;
import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.databases.repositories.ProductRepository;
import com.minchev.plantlab.models.forms.LabSaveForm;
import com.minchev.plantlab.validations.anotations.Validator;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.validation.Errors;

@Validator
public class LabSaveValidator implements org.springframework.validation.Validator{

    private final LabRepository labRepository;
    private final PlantRepository plantRepository;
    private final ProductRepository productRepository;


    public LabSaveValidator(LabRepository labRepository,
                            PlantRepository plantRepository,
                            ProductRepository productRepository) {

        this.labRepository= labRepository;
        this.plantRepository= plantRepository;
        this.productRepository= productRepository;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return LabSaveForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LabSaveForm labSaveForm = (LabSaveForm) o;

        if (labSaveForm.getPlant()==null || labSaveForm.getPlant().isEmpty()) {
            errors.rejectValue(
                    "plant",
                    String.format(ValidationConstants.NOT_EXIST, "Plant"),
                    String.format(ValidationConstants.NOT_EXIST, "Plant")
            );
        }

        if ((labSaveForm.getPlant()!=null && !labSaveForm.getPlant().isEmpty()) && !this.plantRepository.findById(labSaveForm.getPlant()).isPresent()) {
            errors.rejectValue(
                    "plant",
                    String.format(ValidationConstants.NOT_EXIST, "Plant"),
                    String.format(ValidationConstants.NOT_EXIST, "Plant")
            );
        }

        if (labSaveForm.getProduct()==null || labSaveForm.getProduct().isEmpty()) {
            errors.rejectValue(
                    "product",
                    String.format(ValidationConstants.NOT_EXIST, "product"),
                    String.format(ValidationConstants.NOT_EXIST, "product")
            );
        }

        if (!labSaveForm.getProduct().isEmpty() && !this.productRepository.findById(labSaveForm.getProduct()).isPresent()) {
            errors.rejectValue(
                    "product",
                    String.format(ValidationConstants.NOT_EXIST, "Product"),
                    String.format(ValidationConstants.NOT_EXIST, "Product")
            );
        }





    }
}
