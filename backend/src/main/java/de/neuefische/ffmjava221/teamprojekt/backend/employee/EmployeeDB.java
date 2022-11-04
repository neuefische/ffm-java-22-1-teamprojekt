package de.neuefische.ffmjava221.teamprojekt.backend.employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDB extends MongoRepository<Employee, String> {


}
