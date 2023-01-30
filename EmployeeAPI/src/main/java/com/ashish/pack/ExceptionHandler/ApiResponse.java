package com.ashish.pack.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ToString
public class ApiResponse {
    int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String errorMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Map<String , String> errorMap;
}
