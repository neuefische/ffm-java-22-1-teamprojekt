package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;


    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    public List<EmployeeCard> getAllEmployees() {
        return employeeRepo.getAll();
    }
}