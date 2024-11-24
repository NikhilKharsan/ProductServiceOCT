package com.scaler.productservice.advices;

import com.scaler.productservice.dtos.ErrorDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNPEException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong");

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(501));

        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handlePNFException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Product Not found. Please try again");

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto,
                HttpStatusCode.valueOf(404));

        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public void handleAllExceptions() {

    }
}
