package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Region;
import com.company.repository.Inter.RegionRepositoryInter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegionRepositoryImpl implements RegionRepositoryInter {
    private Region getRegion(ResultSet rs)throws Exception{
       var id= rs.getLong("region_id");
        var name=rs.getString("region_name");
        return new Region(id,name);
    }
    @Override
    public boolean deleteByName(String name) {

        boolean isDeleted = false;
        var conn = DbConfig.instance();
        var sql = " delete from regions where region_name = ?; ";
        try (
                var statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            var affected = statement.executeUpdate();
            if (affected > 0) {
                isDeleted = true;
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isDeleted;
    }


    @Override
    public List<Region> allRegion() throws SQLException {

        List<Region> region = new ArrayList<>();
        var sql = "select * from regions ; ";
        try (Connection con =DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                region.add(getRegion(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public Optional<Region> findById(long id) {
        var sql = """
                select * from regions  where region_id = ?;
                """;
        Region region = null;
        try ( var conn = DbConfig.instance();
              var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    region = getRegion(rs);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(region);
    }


}
