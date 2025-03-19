package com.ipi.jva324.gestionchampionnantapi.exceptionHandler;

import com.ipi.jva324.gestionchampionnantapi.models.ErrorValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // on crée la liste des erreurs
        List<ErrorValidation> errors = new ArrayList<>();

        // on récupère le bindingResult
        BindingResult bindingResult = ex.getBindingResult();

        // on récupère la liste des erreurs de validation
        List<FieldError> fieldErrors = ex.getFieldErrors();

        for (FieldError fieldError : fieldErrors){
            errors.add(new ErrorValidation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return this.handleExceptionInternal(ex, errors, headers, status, request);
    }
}
