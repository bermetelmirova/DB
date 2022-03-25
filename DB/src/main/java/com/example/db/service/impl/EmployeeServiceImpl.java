package com.example.db.service.impl;

import com.example.db.entity.Employee;
import com.example.db.repository.EmployeeRepository;
import com.example.db.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public Employee deleteById(Long id) {
        Employee employee = getById(id);
        if (employee == null)
            throw new NullPointerException();
        employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public Employee update(Employee entity) {
        Employee employee = getById(entity.getId());
        employee.setAddress(entity.getAddress());
        employee.setFullName(entity.getFullName());
        employee.setPayment(entity.getPayment());
        employee.setPosition(entity.getPosition());
        employee.setPhoneNumber(entity.getPhoneNumber());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
