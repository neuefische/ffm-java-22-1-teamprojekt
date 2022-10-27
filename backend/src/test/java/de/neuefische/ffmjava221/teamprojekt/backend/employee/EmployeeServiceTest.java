package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    EmployeeUtils employeeUtils = mock(EmployeeUtils.class);
    EmployeeRepo employeeRepo = mock(EmployeeRepo.class);
    EmployeeService employeeService = new EmployeeService(employeeRepo, employeeUtils);

    @Test
    @DirtiesContext
    void getAllEmployeesTEST() {
        //given
        List<Employee> testEmployees = new ArrayList<>();
        //when
        List<Employee> actual = employeeService.getAllEmployees();
        //then
        assertEquals(testEmployees, actual);
    }

    @Test
    @DirtiesContext
    void addEmployeeTEST() {
        //given
        String id = "123";
        Employee employeeWithId = new Employee(id, "Hasi");
        NewEmployee employeeWithoutId = new NewEmployee("Hasi");
        //when
        when(employeeUtils.generateUUID()).thenReturn(id);
        when(employeeRepo.addEmployee(employeeWithId)).thenReturn(employeeWithId);
        Employee actual = employeeService.addEmployee(employeeWithoutId);
        //then
        assertEquals(employeeWithId, actual);
    }

    @Test
    @DirtiesContext
    void deleteEmployeeTEST() {
        //given
        String testToDeleteString = "UUIDFromController";
        //when
        String actual = employeeService.deleteEmployee(testToDeleteString);
        //then
        String expected = null;
        assertEquals(expected, actual);
    }
}