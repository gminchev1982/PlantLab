package com.minchev.plantlab.models.Api;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseModel {
    private Integer status;
    private String message;
    private List<ObjectError> errors;
}
