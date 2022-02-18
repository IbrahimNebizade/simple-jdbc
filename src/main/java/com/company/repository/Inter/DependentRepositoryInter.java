package com.company.repository.Inter;

import com.company.domain.Dependent;

import java.util.List;
import java.util.Optional;

public interface DependentRepositoryInter {
    List<Dependent> allDependent();
    Optional<Dependent> selectByName(String name);
    boolean deleteByName(String name);
}
