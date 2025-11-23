package com.employeepayrolladv.service;

 

import com.employeepayrolladv.model.Employee;

import java.util.List;
import java.util.Optional;

public interface PayrollService {
    void addEmployee(Employee e);
    Optional<Employee> findById(int id);
    double calculateNetPay(Employee e);
    List<Employee> listAll();
    double totalPayroll(); // sum of all net pays
}
