package com.ashish.pack.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @NotNull
    @Min(value = 1000,message = "Cannot be less than 1000")
    @Max(value = 9999,message = "Cannot be greater than 1000")
    private int employeeId;
    @NotBlank(message = "Employee name can't be null or empty")
    private String name;
    @NotBlank(message = "Department Name can't be null or empty")
    private String departmentName;

    @Override
    public String toString(){
        return name + " "+employeeId + " " + departmentName;
    }
}
