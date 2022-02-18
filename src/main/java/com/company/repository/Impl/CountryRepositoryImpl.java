package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Country;
import com.company.domain.Region;
import com.company.repository.Inter.CountryRepositoryInter;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CountryRepositoryImpl implements CountryRepositoryInter {

    private static Country getCountry(ResultSet rs) throws SQLException {

        String countryId = rs.getString("country_id");
        String countryName = rs.getString("country_name");
        long regionId = rs.getLong("region_id");

        return new Country(countryId, countryName, new Region(regionId, null));
    }

    @Override
    public Optional<Country> selectById(String countryId) {

        Country country = null;
        var sql = """
                select * from countries
                where country_id=?;
                """;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, countryId);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                country = getCountry(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(country);
    }

    @Override
    public boolean updateCountryName(String countryId, String countryName) {
        boolean update = false;
        var sql = """
                update countries set country_name=? where country_id=?;
                """;
        var conn = DbConfig.instance();
        try (var statement = conn.prepareStatement(sql)) {
            statement.setString(1, countryId);
            statement.setString(2, countryName);
            var affected = statement.executeUpdate();
            if (affected > 0) {
                update = true;
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

    @Override
    public List<Country> allCountry() {
        List<Country> cList = new ArrayList<>();
        var sql = """
                select *
                from countries;
                """;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                Country c = getCountry(rs);
                cList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cList;
    }

    @Override
    public boolean deleteCountryById(String id) {
        var sql = """
                      delete from countries where country_id=?;         
                """;
        boolean delete = false;
        var con = DbConfig.instance();
        try {
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            var affected = stmt.executeUpdate();
            if (affected > 0) {
                delete = true;
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return delete;
    }
}
