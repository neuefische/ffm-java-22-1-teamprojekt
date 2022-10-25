package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeRepo {
    List<EmployeeCard> employeeList = new ArrayList<>(List.of(
            new EmployeeCard("1","Marc"),
            new EmployeeCard("2","Chris"),
            new EmployeeCard("3","Dennis")
    ));

    public List<EmployeeCard> getAll() {
        return employeeList;
    }
}



