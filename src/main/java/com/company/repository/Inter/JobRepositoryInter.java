package com.company.repository.Inter;

import com.company.domain.Job;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface JobRepositoryInter {
    List<Job> allJob();
    Optional<Job>selectJobByTitle(String title);
    boolean updateJobMinMaxSalary(String title, BigDecimal minSalary,BigDecimal maxSalary);
    boolean deleteById(long id);
}
