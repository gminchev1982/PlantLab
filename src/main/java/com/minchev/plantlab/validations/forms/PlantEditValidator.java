package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.database.repositories.PlantRepository;
import com.minchev.plantlab.models.forms.PlantEditForm;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.validation.Errors;

@Validator
public class PlantEditValidator implements org.springframework.validation.Validator {

    private final PlantRepository plantRepository;

    public PlantEditValidator(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PlantEditForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlantEditForm plantEditForm = (PlantEditForm) o;
        if (plantEditForm.getBarcode() == null || plantEditForm.getBarcode() == "" || plantEditForm.getBarcode().isEmpty()) {

            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_LENGTH,
                    ValidationConstants.BARCODE_LENGTH
            );
        }

        if (!plantEditForm.getBarcode().isEmpty() && (plantEditForm.getBarcode().length() < 20 || plantEditForm.getBarcode().length() > 20)) {
            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_LENGTH,
                    ValidationConstants.BARCODE_LENGTH

            );
        }


        if (!plantEditForm.getBarcodeold().contentEquals(plantEditForm.getBarcode()) && this.plantRepository.findByBarcode(plantEditForm.getBarcode()).isPresent()) {
            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_EXIST,
                    ValidationConstants.BARCODE_EXIST
            );
        }
    }
}
