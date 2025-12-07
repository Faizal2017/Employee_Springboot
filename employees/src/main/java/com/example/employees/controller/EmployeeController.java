package com.example.employees.controller;

import com.example.employees.entity.Employee;
import com.example.employees.request.EmployeeRequest;
import com.example.employees.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/employees")
@RestController
public class EmployeeController {


   EmployeeService employeeService;

   @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService =  employeeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> findAll(){
      return  employeeService.findAll();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public Employee save(@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee emp = employeeService.save(employeeRequest);
       return emp;

    }


    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
       return employeeService.findById(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public Employee update(@Valid @RequestBody EmployeeRequest requestBody , @PathVariable @Min(value = 1) long id){
       return employeeService.update(id,requestBody);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){

       employeeService.delete(id);
    }
}
