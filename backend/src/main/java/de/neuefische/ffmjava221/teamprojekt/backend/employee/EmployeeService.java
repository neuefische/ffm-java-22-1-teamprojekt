package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeUtils employeeUtils;
    private final EmployeeInterface employeeInterface;

    public EmployeeService(EmployeeUtils employeeUtils,
                            EmployeeInterface employeeInterface) {
        this.employeeUtils = employeeUtils;
        this.employeeInterface = employeeInterface;
    }

    public List<Employee> getAllEmployees() {
        return employeeInterface.findAll();
    }

    public Employee addEmployee(NewEmployee newEmployee) {
        String id = employeeUtils.generateUUID();
        Employee saveEmployee = newEmployee.withId(id);
        return employeeInterface.save(saveEmployee);
    }

    public Optional<Employee> deleteEmployee(String id) {
            Optional<Employee> deleteEmployee = employeeInterface.findById(id);
            employeeInterface.deleteById(id);
            return deleteEmployee;
    }

    public Employee updateEmployee(Employee employee) {
       return employeeInterface.save(employee);
    }
}
