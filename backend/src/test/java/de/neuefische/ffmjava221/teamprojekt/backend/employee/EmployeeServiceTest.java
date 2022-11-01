package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    EmployeeUtils employeeUtils = mock(EmployeeUtils.class);
    //EmployeeRepo employeeRepo = mock(EmployeeRepo.class);
    EmployeeInterface employeeInterface = mock(EmployeeInterface.class);
    EmployeeService employeeService = new EmployeeService(employeeUtils, employeeInterface);


    @Test
    void getAllEmployeesTest() {
        //given
        List<Employee> testEmployees = new ArrayList<>();
        //when
        List<Employee> actual = employeeService.getAllEmployees();
        //then
        assertEquals(testEmployees, actual);
    }

//    @Test
//    void addEmployeeTest() {
//        //given
//        String id = "123";
//        Employee employeeWithId = new Employee(id, "Hasi");
//        NewEmployee employeeWithoutId = new NewEmployee("Hasi");
//        //when
//        when(employeeUtils.generateUUID()).thenReturn(id);
//        when(employeeRepo.addEmployee(employeeWithId)).thenReturn(employeeWithId);
//        Employee actual = employeeService.addEmployee(employeeWithoutId);
//        //then
//        assertEquals(employeeWithId, actual);
//    }

//    @Test
//    void deleteEmployeeTest() {
//        //given
//        String testToDeleteString = "UUIDFromController";
//        Employee testEmployee = new Employee(testToDeleteString, "Hasi");
//        //when
//        when(employeeRepo.deleteEmployee(testToDeleteString)).thenReturn(testEmployee);
//        Optional<Employee> actual = employeeService.deleteEmployee(testToDeleteString);
//        //then
//        assertEquals(testEmployee, actual);
//    }

//    @Test
//    void updateEmployeeRabbitToHoppelTest() {
//        //given
//        Employee testEmployee = new Employee("123", "Rabbit");
//        Employee updatedEmployee = new Employee("123", "Hoppel");
//        //when
//        when(employeeRepo.updateEmployee(testEmployee)).thenReturn(updatedEmployee);
//        Employee actual = employeeService.updateEmployee(testEmployee);
//        //then
//        assertEquals(updatedEmployee, actual);
//    }
}
