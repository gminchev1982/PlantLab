package com.minchev.plantlab.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.minchev.plantlab.validations.constants.ValidationConstants.SAVE_WRONG;

@Component
public class CommonUtils {

    public CommonUtils() {

    }


    public ResponseEntity getApiResult (Boolean result, String[] message) {
        if (result.valueOf(true))    return ResponseEntity.status(HttpStatus.OK).body(message);
        else return ResponseEntity.status(400).body(SAVE_WRONG);
    }


}
