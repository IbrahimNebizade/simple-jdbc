package com.company.repository.Inter;

import com.company.domain.Region;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RegionRepositoryInter {
    Optional<Region> findById(long id);
    boolean deleteByName(String name);
    List<Region> allRegion() throws SQLException;
}
