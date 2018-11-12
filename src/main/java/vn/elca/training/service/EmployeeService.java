package vn.elca.training.service;

import java.util.List;

import vn.elca.training.dom.Employee;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> saveAll(List<Employee> employees);

    List<Employee> getAll();
}