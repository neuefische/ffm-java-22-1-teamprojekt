package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.http.HttpStatus;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    Employee addEmployee(@RequestBody NewEmployee newEmployee) {
        return employeeService.addEmployee(newEmployee);
    }

    @DeleteMapping("{id}")
    public Employee deleteEmployee(@PathVariable String id){
        return employeeService.deleteEmployee(id);
    }
}
