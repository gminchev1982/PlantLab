package com.minchev.plantlab.controllers.rest;

import com.minchev.plantlab.models.forms.PlantEditForm;
import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.models.forms.UserRegisterForm;
import com.minchev.plantlab.models.rest.PlantResponseModel;
import com.minchev.plantlab.models.service.PlantServiceEditModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.servicies.PlantService;
import com.minchev.plantlab.validations.PlantEditValidator;
import com.minchev.plantlab.validations.PlantSaveValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.minchev.plantlab.validations.constants.ValidationConstants.BARCODE_DONE;

@RestController
@RequestMapping("/api/plants")
public class PlantApiController {

    private final PlantSaveValidator plantSaveValidator;
    private final PlantResponseModel plantResponseModel;
    private final ModelMapper modelMapper;
    private final PlantService plantService;
    private final PlantEditValidator plantEditValidator;
    private final PlantServiceEditModel plantServiceEditModel;

    public PlantApiController(PlantSaveValidator plantSaveValidator,
                              PlantEditValidator plantEditValidator,
                              PlantResponseModel plantResponseModel,
                              PlantServiceEditModel plantServiceEditModel,
                              PlantService plantService,
                              ModelMapper modelMapper) {
        this.plantSaveValidator = plantSaveValidator;
        this.plantResponseModel = plantResponseModel;
        this.plantService = plantService;
        this.modelMapper = modelMapper;
        this.plantEditValidator = plantEditValidator;
        this.plantServiceEditModel = plantServiceEditModel;


    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody PlantSaveForm plantForm, Errors errors) {

        this.plantSaveValidator.validate(plantForm, errors);
        if (errors.hasErrors()) {
            plantResponseModel.setFlag(false);
            plantResponseModel.setMessage(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(plantResponseModel);
        }

        PlantServiceModel plantServiceModel = this.modelMapper.map(plantForm, PlantServiceModel.class);
        this.plantService.save(plantServiceModel);

        plantResponseModel.setMessage(BARCODE_DONE);
        plantResponseModel.setFlag(true);
        return ResponseEntity.ok(plantResponseModel);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPlant(@PathVariable String id) {
        plantResponseModel.setResult(this.modelMapper.map(this.plantService.findPlantById(id), PlantServiceModel.class));
        plantResponseModel.setFlag(true);
        return ResponseEntity.status(HttpStatus.OK).body(plantResponseModel);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody PlantEditForm plantForm, Errors errors) {
       this.plantEditValidator.validate(plantForm, errors);
        if (errors.hasErrors()) {
            plantResponseModel.setFlag(false);
            plantResponseModel.setResult(null);
            plantResponseModel.setMessage(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(plantResponseModel);
        }

      this.plantService.edit( this.modelMapper.map(plantForm, PlantServiceEditModel.class));
      plantResponseModel.setMessage(BARCODE_DONE);
        plantResponseModel.setFlag(true);
        return ResponseEntity.ok(plantResponseModel);
    }


}
