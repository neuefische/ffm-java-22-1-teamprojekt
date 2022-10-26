package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    Employee addEmployee(@RequestBody NewEmployee newEmployee) {
        return employeeService.addEmployee(newEmployee);
    }

    @DeleteMapping
}
