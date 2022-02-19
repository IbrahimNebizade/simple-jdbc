package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Dependent;
import com.company.domain.Employee;
import com.company.repository.Inter.DependentRepositoryInter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DependentRepositoryImpl implements DependentRepositoryInter {
    private Dependent getDependent(ResultSet rs) throws Exception {
        var id = rs.getLong("dependent_id");
        var name = rs.getString("first_name");
        var surname = rs.getString("last_name");
        var relation = rs.getString("relationship");
        var eId = rs.getLong("employee_id");
        Employee emp = new Employee(eId);
        return new Dependent(id, name, surname, relation, emp);
    }

    @Override
    public List<Dependent> allDependent() {
        List<Dependent> list = new ArrayList<>();
        var sql = """
                """;
        try (var con = DbConfig.instance();
            var stmt = con.prepareStatement(sql)) {
            stmt.execute();
            try(var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    list.add(getDependent(rs));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<Dependent> selectByName(String name) {
        Dependent dependent = null;
        var sql = """
                select *
                from dependents where first_name=?;
                """;
        try (var con = DbConfig.instance();
            var stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.execute();
           try( var rs = stmt.getResultSet()) {
               while (rs.next()) {
                   dependent = getDependent(rs);
               }
           }catch (Exception ex){
               ex.printStackTrace();
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(dependent);
    }

    @Override
    public boolean deleteByName(String name) {
        boolean delete = false;
        var sql = """
                delete from dependents where first_name=?;
                """;
        var con = DbConfig.instance();
        try (var stmt = con.prepareStatement(sql)){
            stmt.setString(1, name);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                delete = true;
            }
            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception exception) {
                exception.printStackTrace();
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
