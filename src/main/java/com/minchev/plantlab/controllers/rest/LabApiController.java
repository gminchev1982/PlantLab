package com.minchev.plantlab.controllers.rest;

import com.minchev.plantlab.models.forms.LabEditForm;
import com.minchev.plantlab.models.forms.LabSaveForm;
import com.minchev.plantlab.servicies.LabService;
import com.minchev.plantlab.validations.forms.LabSaveValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.minchev.plantlab.validations.constants.ValidationConstants.BARCODE_DONE;

@RestController
@RequestMapping("/api/labs")
public class LabApiController {

    private final LabSaveValidator labSaveValidator;
    private final LabService labService;


    public LabApiController(LabSaveValidator labSaveValidator, LabService labService) {
        this.labSaveValidator= labSaveValidator;
        this.labService = labService;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody LabSaveForm labSaveForm, Errors errors, Principal principal) {
        this.labSaveValidator.validate(labSaveForm, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }

        this.labService.save(labSaveForm,  principal);

        return ResponseEntity.status(HttpStatus.OK).body(BARCODE_DONE);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLab(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body(BARCODE_DONE);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> edit(@RequestBody LabEditForm labEditForm, Errors errors) {

        return ResponseEntity.status(HttpStatus.OK).body(BARCODE_DONE);
    }

}
