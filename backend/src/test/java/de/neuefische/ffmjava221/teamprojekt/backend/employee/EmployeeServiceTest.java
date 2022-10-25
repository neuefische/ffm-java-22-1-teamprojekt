package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    EmployeeService employeeService = mock(EmployeeService.class);
    EmployeeUtils employeeUtils = mock(EmployeeUtils.class);
    EmployeeRepo employeeRepo = mock(EmployeeRepo.class);


    @Test
    void getAllEmployeesTEST() {
        //given
        List<Employee> testEmployees = new ArrayList<>();
        //when
        List<Employee> actual = employeeService.getAllEmployees();

        //then
        List<Employee> expected = testEmployees;
        assertEquals(expected, actual);
    }

    @Test
    void addEmployeeTEST() {
        String id = EmployeeUtils.generateUUID();
        System.out.println("UUID: " + id);

        //given
        Employee employee = new Employee(id, "Hasi");
        employeeService.addEmployee(employee);
        System.out.println("Employee :" + employee);

//        when(employeeUtils.generateUUID()).thenReturn(id);
        Employee actual = employeeService.getAllEmployees().get(0);

        //String actual = employeeService.toString();
//        Employee actual = employee;

        //then
        Employee expected = employee;
        assertEquals(expected, actual);
    }

    @Test
    @DirtiesContext
    void addEmployee2Test(){
        //EmployeeService employeeService1 = new EmployeeService()
        //GIVEN
        //when(employeeUtils.)
        //WHEN
        //THEN
    }

    
}