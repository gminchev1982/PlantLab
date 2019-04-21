package com.minchev.plantlab.controllers.rest;

import com.minchev.plantlab.models.forms.PlantEditForm;
import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.models.service.ProductServiceModel;
import com.minchev.plantlab.servicies.PlantService;
import com.minchev.plantlab.validations.PlantEditValidator;
import com.minchev.plantlab.validations.PlantSaveValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.minchev.plantlab.validations.constants.ValidationConstants.BARCODE_DONE;

@RestController
@RequestMapping("/api/plants")
public class PlantApiController {

    private final PlantSaveValidator plantSaveValidator;
    private final ModelMapper modelMapper;
    private final PlantService plantService;
    private final PlantEditValidator plantEditValidator;

    public PlantApiController(PlantSaveValidator plantSaveValidator,
                              PlantEditValidator plantEditValidator,
                              PlantService plantService,
                              ModelMapper modelMapper) {
        this.plantSaveValidator = plantSaveValidator;
        this.plantService = plantService;
        this.modelMapper = modelMapper;
        this.plantEditValidator = plantEditValidator;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody PlantSaveForm plantForm, Errors errors) {
        this.plantSaveValidator.validate(plantForm, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        PlantServiceModel plantServiceModel = this.plantService.save(this.modelMapper.map(plantForm, PlantServiceModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(BARCODE_DONE);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPlant(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.modelMapper.map(this.plantService.findPlantById(id), PlantServiceModel.class));
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody PlantEditForm plantForm, Errors errors) {

        this.plantEditValidator.validate(plantForm, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        this.plantService.edit(this.modelMapper.map(plantForm, PlantServiceEditModel.class));
        return ResponseEntity.status(HttpStatus.OK).body(BARCODE_DONE);
    }


}
