package com.ashish.pack.ExceptionHandler;

import com.ashish.pack.Exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    ApiResponse apiResponse;

    @ExceptionHandler({EmployeeNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleEmployeeNotFoundException(Exception exception){

        //return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        apiResponse = ApiResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .build();
        return  apiResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.info("Entry handleMethodArgumentNotValidException {}");
       /* final String[] errorResponse = {""};*/
        Map<String,String> fieldMap = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error-> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            /*errorResponse[0] =  (error != null) ? errorResponse[0] + fieldName + "\n" + errorMessage + ", " : errorResponse[0];*/
            fieldMap.put(fieldName, errorMessage);
        });
        //return new ApiResponse(HttpStatus.BAD_REQUEST.value(), /*errorResponse[0]*/);
        log.info("fieldMap ->"+ fieldMap);
        apiResponse = ApiResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .errorMap(fieldMap)
                .build();
        log.info("apiResponse -> "+ apiResponse);
        return  apiResponse;
    }


}
