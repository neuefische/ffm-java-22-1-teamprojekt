package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    EmployeeUtils employeeUtils = mock(EmployeeUtils.class);
    EmployeeDB employeeDB = mock(EmployeeDB.class);
    EmployeeService employeeService = new EmployeeService(employeeUtils, employeeDB);

    @Test
    void getAllEmployeesTest() {
        //given
        List<Employee> testEmployees = new ArrayList<>();
        //when
        List<Employee> actual = employeeService.getAllEmployees();
        //then
        assertEquals(testEmployees, actual);
    }

    @Test
    void addEmployeeTest() {
        //given
        String id = "123";
        String date = "12.03.1944";
        Employee employeeWithId = new Employee(id, "Hasi",date);
        NewEmployee employeeWithoutId = new NewEmployee("Hasi");
        //when
        when(employeeUtils.generateUUID()).thenReturn(id);
        when(employeeUtils.generateINSTANT()).thenReturn(date);
        when(employeeDB.save(employeeWithId)).thenReturn(employeeWithId);
        Employee actual = employeeService.addEmployee(employeeWithoutId);
        //then
        assertEquals(employeeWithId, actual);
    }

    @Test
    void deleteEmployeeTest() {
        //given
        String testToDeleteString = "UUIDFromController";
        Employee testEmployee = new Employee(testToDeleteString, "Hasi", "12.13.2055");
        //when
        when(employeeDB.findById(testToDeleteString)).thenReturn(Optional.of(testEmployee));
        Optional <Employee> actual = employeeService.deleteEmployee(testToDeleteString);
        //then
        assertEquals(testEmployee, actual.get());
    }

    @Test
    void updateEmployeeRabbitToHoppelTest() {
        //given
        String idFromUpdateEmployee = "UUIDFromController";
        Employee testEmployee = new Employee(idFromUpdateEmployee, "Rabbit", "12.13.2055");
        Employee updatedEmployee = new Employee(idFromUpdateEmployee, "Hoppel", "12.13.2055");
        //when
        when(employeeDB.findById(idFromUpdateEmployee)).thenReturn(Optional.of(testEmployee));
        when(employeeDB.save(testEmployee)).thenReturn(updatedEmployee);
        Employee actual = employeeService.updateEmployee(testEmployee);
        //then
        assertEquals(updatedEmployee, actual);
    }
}
