package com.ashish.pack.Controller;

import com.ashish.pack.Exception.EmployeeNotFoundException;
import com.ashish.pack.Entity.EmployeeEntity;
import com.ashish.pack.Model.AuthRequest;
import com.ashish.pack.Model.Employee;
import com.ashish.pack.Service.EmployeeService;
import com.ashish.pack.Service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Configuration
@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Value("${server.port}")
    int port;

    @Value("${spring.messages}")
    String message;

    @Autowired
    JwtService jwtService;

    //This method validates the username & password and then generates the token and return it back
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest){
        return jwtService.generateToken(authRequest.getUserName());
    }

    @GetMapping(path = "/employee/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeEntity/*ResponseEntity<Employee>*/ getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(id);
        //return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/employee")
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeEntity> getAllEmployee(){
        return employeeService.getEmployeeAllEpmolyee();
    }

    @PostMapping(path = "/employee")
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmployeeEntity saveEmployee(@RequestBody @Valid Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping(path = "/employee/rp", produces = {MediaType.APPLICATION_XML_VALUE
                                                  ,MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeEntity> getEmployeeByName(@RequestParam String name){
        log.info("getEmployeeByName request param : "+ name );
        return employeeService.getEmployeeAllEpmolyee(name);
    }

    @GetMapping("/environment")
    @ResponseStatus(value = HttpStatus.OK)
    public String getEnvironmentDetails(){
        String environmentDetails = "port : "+ port + "\n"+ "message : "+ message;
        log.info("environmentDetails -> "+ environmentDetails);
        return environmentDetails;
    }
}
