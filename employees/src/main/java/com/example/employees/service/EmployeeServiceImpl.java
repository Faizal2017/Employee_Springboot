package com.example.employees.service;

import com.example.employees.dao.EmployeeRepository;
import com.example.employees.entity.Employee;
import com.example.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Employee EmployeeEntity;
    EmployeeRepository  employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository ){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
      Optional<Employee> result =  employeeRepository.findById(id);

      Employee employee = null;

      if(result.isPresent()){
          employee = result.get();
      }else {
          throw new RuntimeException("Employee not found");
      }
      return employee;
    }

    @Override
    public Employee save(EmployeeRequest employeeRequest) {
       Employee employee = convertToEmployee(0,employeeRequest);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id,employeeRequest);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(long id) {
       employeeRepository.deleteById(id);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(),employeeRequest.getLastName(),employeeRequest.getEmail());
    }


}
