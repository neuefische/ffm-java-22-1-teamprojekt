package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Optional<Employee> deleteEmployee(@PathVariable @Valid String id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(path = {"{id}"})
    @ResponseStatus(code = HttpStatus.CREATED)
    Employee update(@PathVariable String id, @RequestBody Employee employee) {
        if (!employee.id().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id in the url does not match the request body's id");
        }
        return employeeService.updateEmployee(employee);
    }
}
