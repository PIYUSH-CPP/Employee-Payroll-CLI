package com.employeepayrolladv.service;

 

import com.employeepayrolladv.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PayrollServiceImpl implements PayrollService {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void addEmployee(Employee e) {
        // simple validation: check duplicate id
        if (e == null) throw new IllegalArgumentException("employee cannot be null");
        if (findById(e.getId()).isPresent()) {
            throw new IllegalArgumentException("Employee with id " + e.getId() + " already exists");
        }
        employees.add(e);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();
    }

    @Override
    public double calculateNetPay(Employee e) {
        if (e == null) throw new IllegalArgumentException("employee cannot be null");
        return e.calculateNetPay(); // polymorphism: subclass compute
    }

    @Override
    public List<Employee> listAll() {
        // return a copy (defensive)
        return employees.stream().collect(Collectors.toList());
    }

    @Override
    public double totalPayroll() {
        return employees.stream()
                .mapToDouble(Employee::calculateNetPay)
                .sum();
    }
}
