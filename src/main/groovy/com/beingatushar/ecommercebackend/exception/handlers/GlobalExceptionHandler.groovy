package com.beingatushar.ecommercebackend.exception.handlers

import com.beingatushar.ecommercebackend.exception.definations.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>()
        error.put("error", ex.getMessage())
        error.put("status", HttpStatus.NOT_FOUND.toString())
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>()
        error.put("error", "Something went wrong!")
        error.put("message", ex.getMessage())
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString())
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}