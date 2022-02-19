package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Country;
import com.company.domain.Location;
import com.company.repository.Inter.LocationRepositoryInter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryImpl implements LocationRepositoryInter {
    private Location getLocation(ResultSet rs) throws Exception {
        var id = rs.getLong("location_id");
        var adress = rs.getString("street_address");
        var postal = rs.getString("postal_code");
        var city = rs.getString("city");
        var state = rs.getString("state_province");
        var cId = rs.getString("country_id");
        Country country = new Country(cId);
        return new Location(id, adress, postal, city, state, country);
    }

    @Override
    public Optional<Location> selectByPostalCode(String postalCode) {
        Location loc = null;
        var sql = """
                select *
                from locations where postal_code=?;
                """;
        try (var con = DbConfig.instance();
            var stmt = con.prepareStatement(sql)) {
            stmt.setString(1, postalCode);
            stmt.execute();
            try(var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    loc = getLocation(rs);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(loc);
    }

    @Override
    public List<Location> allLocation() {
        List<Location> locations = new ArrayList<>();
        var sql = """
                """;
        try (var con = DbConfig.instance();
            var stmt = con.prepareStatement(sql)) {
            stmt.execute();
            try(var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    locations.add(getLocation(rs));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public boolean updateByPostalCode(String streetAdress, String postalCode) {
        boolean update = false;
        var sql = """
                update locations set street_address=? where postal_code=?;
                """;
        var con = DbConfig.instance();
        try (var stmt = con.prepareStatement(sql)){
            stmt.setString(1, streetAdress);
            stmt.setString(2, postalCode);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                update = true;
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                e.printStackTrace();
            }

        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    @Override
    public boolean deleteById(long id) {
        boolean delete = false;
        var sql = """
                delete from locations where location_id=?;
                """;
        var con = DbConfig.instance();
        try ( var stmt = con.prepareStatement(sql)){
            stmt.setLong(1, id);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                delete = true;
            }
            con.commit();
        } catch (Exception er) {
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return delete;
    }
}
