package com.company.repository.Inter;

import com.company.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepositoryInter {
    Optional<Location> selectByPostalCode(String postalCode );
    List<Location> allLocation();
    boolean updateByPostalCode(String StreetAdress,String postalCode);
    boolean deleteById(long id);
}
