package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.data.mongodb.repository.MongoRepository;
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
        String regTimeStamp = employeeUtils.generateINSTANT();
        Employee saveEmployee = newEmployee.withIdAndTimeStamp(id, regTimeStamp);
        return employeeInterface.save(saveEmployee);
    }

    public Optional<Employee> deleteEmployee(String id) {
        Optional<Employee> deleteEmployee = employeeInterface.findById(id);
        employeeInterface.deleteById(id);
        return deleteEmployee;
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employeeToUpdate = employeeInterface.findById(employee.id());
        Employee updateEmployee = UpdateEmployee.toUpdateTimeStamp(
                employeeToUpdate.get().id(),
                employee.name(),
                employeeToUpdate.get().regTimeStamp());

        return employeeInterface.save(updateEmployee);
    }
}
