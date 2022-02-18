package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Country;
import com.company.domain.Department;
import com.company.domain.Location;
import com.company.repository.Inter.DepartmentRepositoryInter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentRepositoryImpl implements DepartmentRepositoryInter {

    private Department getDepartment(ResultSet rs) throws Exception {
        var id = rs.getLong("department_id");
        var name = rs.getString("department_name");
        var lId = rs.getLong("location_id");
        return new Department(id, name, new Location(lId));
    }

    @Override
    public Optional<Department> selectById(long id) {
        var sql = """
                select *
                from departments where department_id=?;
                """;
        Department department = null;
        try (var conn = DbConfig.instance()) {
            var stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                department = getDepartment(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(department);
    }

    @Override
    public boolean updateDepartmentName(String name, long id) {
        boolean update = false;
        var sql = """
                update departments set department_name=? where department_id=?;
                """;
        var con = DbConfig.instance();
        try {
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setLong(2, id);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                update = true;
            }
            con.commit();
        } catch (Exception e) {
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
        return update;
    }

    @Override
    public boolean deleteDepartment(long id) {
        boolean delete = false;
        var sql = """
                delete from departments where department_id=?;
                """;
        var con = DbConfig.instance();
        try {
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                delete = true;
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }finally {
            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return delete;
    }

    @Override
    public List<Department> allDepartment() {
        List<Department> departments = new ArrayList<>();
        var sql = """
                select * from departments ;
                """;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                departments.add(getDepartment(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
}
