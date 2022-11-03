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
        String date = "12.03.19444";
        Employee employeeWithId = new Employee(id, "Hasi",date);
        NewEmployee employeeWithoutId = new NewEmployee("Hasi");
        //when
        when(employeeUtils.generateUUID()).thenReturn(id);
        when(employeeUtils.generateINSTANT()).thenReturn(date);
        when(employeeDB.save(employeeWithId)).thenReturn(employeeWithId);
        Employee actual = employeeService.addEmployee(employeeWithoutId);
        //then
        verify(employeeUtils).generateUUID();
        verify(employeeUtils).generateINSTANT();
        verify(employeeDB).save(employeeWithId);
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
        verify(employeeDB).findById(testToDeleteString);
        assertEquals(testEmployee, actual.get());
    }

    @Test
    void updateEmployeeRabbitToHoppelTest() {
        //given
        String idFromUpdateEmployee = "UUIDFromController";
        EmployeeDTO employeeToUpdate = new EmployeeDTO(idFromUpdateEmployee, "Hoppel");
        Employee updatedEmployee = new Employee(idFromUpdateEmployee, "Hoppel", "12.13.2055");
        Employee currentEmployee = new Employee(idFromUpdateEmployee, "Rabbit", "12.13.2055");
        //when
        when(employeeDB.findById(idFromUpdateEmployee)).thenReturn(Optional.of(currentEmployee));
        when(employeeDB.save(updatedEmployee)).thenReturn(updatedEmployee);
        Employee actual = employeeService.updateEmployee(employeeToUpdate);
        //then
        verify(employeeDB).findById(idFromUpdateEmployee);
        verify(employeeDB).save(updatedEmployee);
        assertEquals(updatedEmployee, actual);
    }
}
