package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeUtils employeeUtils;

    public EmployeeService(EmployeeRepo employeeRepo,
                           EmployeeUtils employeeUtils) {
        this.employeeRepo = employeeRepo;
        this.employeeUtils= employeeUtils;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.getAll();
    }

    public Employee addEmployee(Employee employee) {
        String id = employeeUtils.generateUUID();
        Employee saveEmployee = employee.withID(id);
        return employeeRepo.addEmployee(saveEmployee);
    }
}