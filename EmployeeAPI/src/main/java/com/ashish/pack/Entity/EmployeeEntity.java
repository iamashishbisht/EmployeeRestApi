package com.ashish.pack.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {
    @Id
    @Column(name = "id")
    private int employeeId;
    private String name;
    private String departmentName;

    @Override
    public String toString() {
        return name + " " + employeeId + " " + departmentName;
    }

}

