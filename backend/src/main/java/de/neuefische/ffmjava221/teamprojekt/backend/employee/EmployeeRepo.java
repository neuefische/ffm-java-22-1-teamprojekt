package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepo {
    List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAll() {
        return employeeList;
    }

    public Employee addEmployee(Employee saveEmployee) {
        employeeList.add(saveEmployee);
        return saveEmployee;
    }
}



