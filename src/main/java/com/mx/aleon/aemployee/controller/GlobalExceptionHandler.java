package com.mx.aleon.aemployee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mx.aleon.aemployee.ex.ApiException;
import com.mx.aleon.aemployee.ex.BadRequestException;
import com.mx.aleon.aemployee.ex.ResourceNotFoundException;
import com.mx.aleon.aemployee.util.TokenUtil;
import com.mx.aleon.aemployee.vo.GenericResponse;

/**
 * Global class for handling exceptions throughout the application.
 * Uses the {@code @ControllerAdvice} annotation to intercept exceptions
 * and provide customized responses based on the type of error.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles the {@code ResourceNotFoundException}.
     *
     * @param ex The {@code ResourceNotFoundException} thrown when a resource is not found.
     * @return An HTTP response with the error message and {@code 404 Not Found} status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse> handleResourceNotFound(ResourceNotFoundException ex) {
    	log.error("handleResourceNotFound", ex);
    	return buildFail(ex,  HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general exceptions in the application.
     *
     * @param ex The {@code Exception} thrown by unexpected errors.
     * @return An HTTP response with a generic error message and {@code 500 Internal Server Error} status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
    	log.error("handleGeneralException", ex);
        return new ResponseEntity<>("An error has occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the {@code BadRequestException}.
     *
     * @param ex The {@code BadRequestException} thrown by bad requests.
     * @return A customized HTTP response with error details and {@code 400 Bad Request} status.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GenericResponse> handleBadRequestException(BadRequestException ex) {
    	log.error("handleBadRequestException", ex);
    	 return buildFail(ex,  HttpStatus.BAD_REQUEST);

    }
    
    /**
     * Handles the {@code ApiException}.
     *
     * @param ex The {@code ApiException} thrown by bad requests.
     * @return A customized HTTP response with error details and {@code 500 ServerError} status.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GenericResponse> handleApiException(ApiException ex) {
    	return buildFail(ex,  HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    
    private ResponseEntity<GenericResponse> buildFail(ApiException ex, HttpStatus httpStatus) {
    	 GenericResponse response = new GenericResponse(TokenUtil.getToken());
         response.setMessage(ex.getMessage());
         response.setCode(ex.getCode());
        return ResponseEntity.status(httpStatus)
         .contentType(MediaType.APPLICATION_JSON)
         .body(response);
    }
}
