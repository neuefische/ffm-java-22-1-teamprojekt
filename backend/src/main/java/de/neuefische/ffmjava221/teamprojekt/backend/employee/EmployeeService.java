package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.getAll();
    }

    public Employee addEmployee(Employee employee) {
        String id = EmployeeUtils.generateUUID();
        Employee saveEmployee = employee.withID(id);
        return employeeRepo.addEmployee(saveEmployee);
    }
}