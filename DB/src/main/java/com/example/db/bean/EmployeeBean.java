package com.example.db.bean;

import com.example.db.entity.Employee;
import com.example.db.entity.Position;
import com.example.db.service.EmployeeService;
import com.example.db.service.PositionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Component
@ManagedBean
@SessionScoped
@Getter
@Setter
public class EmployeeBean {
    private String fullName;
    private Long positionId;
    private Float payment;
    private String address;
    private String phoneNumber;
    private Employee employee;
    private List<Employee> employees;
    private List<Position> positions;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;

    private void init() {
        employees = employeeService.getAll();
        positions = positionService.getAll();
    }

    private void create() {
        Employee employee = new Employee();
        employee.setAddress(address);
        employee.setPosition(positionService.getById(positionId));
        employee.setPhoneNumber(phoneNumber);
        employee.setFullName(fullName);
        employee.setPayment(payment);
        employeeService.save(employee);
        clean();
    }

    private void delete(Long id) {
        employeeService.deleteById(id);
    }

    private String navigateToUpdate(Long id) {
        employee = employeeService.getById(id);
        return "employee_update.xhtml?faces-redirect=true";
    }

    private void update() {
        employeeService.update(null);
        employee.setAddress(null);
        employee.setPosition(null);
        employee.setPhoneNumber(null);
        employee.setFullName(null);
        employee.setPayment(null);
    }

    private void clean() {
        fullName = null;
        positionId = null;
        payment = null;
        address = null;
        phoneNumber = null;
    }
}
