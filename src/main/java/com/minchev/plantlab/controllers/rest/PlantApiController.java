package com.minchev.plantlab.controllers.rest;

import com.minchev.plantlab.models.forms.PlantSaveForm;
import com.minchev.plantlab.models.rest.PlantResponseModel;
import com.minchev.plantlab.models.service.PlantServiceModel;
import com.minchev.plantlab.servicies.PlantService;
import com.minchev.plantlab.validations.PlantSaveValidator;
import org.modelmapper.ModelMapper;
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

    public PlantApiController(PlantSaveValidator plantSaveValidator, PlantResponseModel plantResponseModel, PlantService plantService, ModelMapper modelMapper) {
        this.plantSaveValidator = plantSaveValidator;
        this.plantResponseModel = plantResponseModel;
        this.plantService = plantService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> updateHosting( @RequestBody PlantSaveForm plantForm, Errors errors) {

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

    @GetMapping("/save")
    public String getSave(){
        return "das";
    }

}
