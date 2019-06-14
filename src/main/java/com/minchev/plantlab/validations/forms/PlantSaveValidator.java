package com.minchev.plantlab.validations.forms;

import com.minchev.plantlab.databases.repositories.PlantRepository;
import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.validations.constants.ValidationConstants;
import org.springframework.validation.Errors;
@Validator
public class PlantSaveValidator implements org.springframework.validation.Validator  {

    private final PlantRepository plantRepository;

    public PlantSaveValidator(PlantRepository plantRepository) {
        this.plantRepository=plantRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PlantSaveForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlantSaveForm PlantSaveForm = (PlantSaveForm) o;
        if (PlantSaveForm.getBarcode()==null || PlantSaveForm.getBarcode()=="" || PlantSaveForm.getBarcode().isEmpty()) {

            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_LENGTH,
                    ValidationConstants.BARCODE_LENGTH
            );
        }

        if ( !PlantSaveForm.getBarcode().isEmpty() && (PlantSaveForm.getBarcode().length() < 20 || PlantSaveForm.getBarcode().length() > 20)) {
            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_LENGTH,
                    ValidationConstants.BARCODE_LENGTH

            );
        }


        if (this.plantRepository.findByBarcode(PlantSaveForm.getBarcode()).isPresent()) {
            errors.rejectValue(
                    "barcode",
                    ValidationConstants.BARCODE_EXIST,
                    ValidationConstants.BARCODE_EXIST
            );
        }
    }
}
