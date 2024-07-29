package com.recipe.mgnt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(RecipeNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ErrorResponse handleRecipeNotFoundException(RecipeNotFoundException ex) {
            return new ErrorResponse("Recipe not found", ex.getMessage());
        }
        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ErrorResponse handleException(Exception ex) {
            return new ErrorResponse("Internal Server Error", ex.getMessage());
        }

        static class ErrorResponse {
            private String error;
            private String message;

            public ErrorResponse(String error, String message) {
                this.error = error;
                this.message = message;
            }

            // Getters and Setters
        }
    }
