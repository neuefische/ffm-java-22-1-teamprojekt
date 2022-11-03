package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeUtils employeeUtils;
    private final EmployeeDB employeeDB;

    public EmployeeService(EmployeeUtils employeeUtils,
                           EmployeeDB employeeDB) {
        this.employeeUtils = employeeUtils;
        this.employeeDB = employeeDB;
    }

    public List<Employee> getAllEmployees() {
        return employeeDB.findAll();
    }

    public Employee addEmployee(NewEmployee newEmployee) {
        String id = employeeUtils.generateUUID();
        String regTimeStamp = employeeUtils.generateINSTANT();
        Employee saveEmployee = newEmployee.withIdAndTimeStamp(id, regTimeStamp);
        return employeeDB.save(saveEmployee);
    }

    public Optional<Employee> deleteEmployee(String id) {
        Optional<Employee> deleteEmployee = employeeDB.findById(id);
        employeeDB.deleteById(id);
        return deleteEmployee;
    }

    public Employee updateEmployee(EmployeeDTO employee) {
        Employee employeeToUpdate = employeeDB.findById(employee.id()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        Employee updateEmployee = EmployeeDTO.toUpdateTimeStamp(
                employeeToUpdate.id(),
                employee.name(),
                employeeToUpdate.regTimeStamp());

        return employeeDB.save(updateEmployee);
    }
}
