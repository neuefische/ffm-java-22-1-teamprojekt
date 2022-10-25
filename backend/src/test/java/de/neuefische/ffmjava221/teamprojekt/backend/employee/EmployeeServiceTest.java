package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EmployeeServiceTest {
    EmployeeService employeeService = mock(EmployeeService.class);


    @Test
    void getAllEmployeesOnEmptyList() {
        //given
        List<EmployeeCard> testEmployees = new ArrayList<>();
        //when
        List<EmployeeCard> actual = employeeService.getAllEmployees();

        //then
        List<EmployeeCard> expected = testEmployees;
        assertEquals(expected, actual);
    }

}