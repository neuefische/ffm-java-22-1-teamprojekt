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

    public String deleteEmployee(String id) {
        for (Employee employeeToDelete : employeeList)
            if (Objects.equals(employeeToDelete.id(), id)) {
                employeeList.remove(employeeToDelete);
                return employeeToDelete.name() + " erfolgreich gelöscht";
            }
        throw new NoSuchElementException("Employee konnte nicht gelöscht werden");
    }
}