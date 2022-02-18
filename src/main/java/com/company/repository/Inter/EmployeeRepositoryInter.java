package com.company.repository.Inter;

import com.company.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryInter {
    Optional<Employee> selectById(long id);

    boolean updateEmail(String email,long id);

    boolean deleteById(long id);

    List<Employee> allEmployee();


}
