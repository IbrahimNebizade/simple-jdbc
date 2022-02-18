package com.company.repository.Inter;

import com.company.domain.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryInter {
Optional<Department> selectById(long id);
boolean updateDepartmentName(String name,long id);
boolean deleteDepartment(long id);
List<Department> allDepartment();
}
