package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Service;

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

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employeeToUpdate = employeeDB.findById(employee.id());
        Employee updateEmployee = UpdateEmployee.toUpdateTimeStamp(
                employeeToUpdate.get().id(),
                employee.name(),
                employeeToUpdate.get().regTimeStamp());

        return employeeDB.save(updateEmployee);
    }
}
