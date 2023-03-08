package com.ashish.pack.Service;

import com.ashish.pack.Entity.Users;
import com.ashish.pack.Exception.EmployeeNotFoundException;
import com.ashish.pack.Entity.EmployeeEntity;
import com.ashish.pack.Model.Employee;
import com.ashish.pack.Repository.EmployeeRepository;
import com.ashish.pack.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeEntity employeeEntity;
    public EmployeeEntity/*ResponseEntity<Employee>*/ getEmployeeById(int id) throws EmployeeNotFoundException {
       Optional<EmployeeEntity> employee = employeeRepository.findById(id);
      // employee.ifPresent(e-> employee.get());
       if(employee.isPresent()) return employee.get();
       else throw new EmployeeNotFoundException("Employee not found for the given ID "+ id);
    }

    public EmployeeEntity saveEmployee(Employee employee) {

        employeeEntity.setEmployeeId(employee.getEmployeeId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setDepartmentName(employee.getDepartmentName());


        log.info("employeeEntity->"+employeeEntity);

/*        save() is causing issue for sequence generation as data was not getting flushed*/
        //EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        EmployeeEntity savedEmployee = employeeRepository.saveAndFlush(employeeEntity);

        return savedEmployee;
    }

    public List<EmployeeEntity> getEmployeeAllEpmolyee() {
        return employeeRepository.findAll();
    }

    public List<EmployeeEntity> getEmployeeAllEpmolyee(String name) {
        log.info("Employee Service -> "+ name);
        return employeeRepository.findByName(name);
    }

    public Users addUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.saveAndFlush(users);
    }
}
