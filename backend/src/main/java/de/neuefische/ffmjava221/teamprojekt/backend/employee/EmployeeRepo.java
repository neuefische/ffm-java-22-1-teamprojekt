package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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

    public Employee deleteEmployee(String id) {
        for (Employee employeeToDelete : employeeList)
            if (Objects.equals(employeeToDelete.id(), id)) {
                employeeList.remove(employeeToDelete);
                return employeeToDelete;
            }
        throw new NoSuchElementException("NoSuchEmployeeFound");
    }

    public Employee updateEmployee(Employee employee) {
        for (Employee employeeToUpdate : employeeList)
            if (Objects.equals(employeeToUpdate.id(), employee.id())) {
                employeeList.remove(employeeToUpdate);
                employeeList.add(employee);
            }
        return employee;
    }
}
