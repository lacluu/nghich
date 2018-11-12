package vn.elca.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.elca.training.dao.EmployeeRepository;
import vn.elca.training.dom.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        List<Employee> result = employeeRepository.save(employees);
        return result;
    }

    @Override
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
}
