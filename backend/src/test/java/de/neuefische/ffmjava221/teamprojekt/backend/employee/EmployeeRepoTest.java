package de.neuefische.ffmjava221.teamprojekt.backend.employee;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepoTest {
    EmployeeRepo employeeRepo = new EmployeeRepo();

    @Test
    @DirtiesContext
    void deleteEmployeeThrowNoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            employeeRepo.deleteEmployee(null);
        });
        String expectedMessage = "Employee konnte nicht gelöscht werden";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}