package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Department;
import com.company.domain.Employee;
import com.company.domain.Job;
import com.company.repository.Inter.EmployeeRepositoryInter;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepositoryInter {
    private Employee getEmployee(ResultSet rs) throws Exception {
        var id = rs.getLong("employee_id");
        var fName = rs.getString("first_name");
        var lName = rs.getString("last_name");
        var email = rs.getString("email");
        var phoneNumber = rs.getString("phone_number");
        var hireDate = rs.getDate("hire_date");
        var jobId = rs.getLong("job_id");
        Job job = new Job(jobId);
        var salary = rs.getBigDecimal("salary");
        var meneger = rs.getLong("manager_id");
        Employee employee = new Employee(meneger);
        var departmentId = rs.getLong("department_id");
        Department department = new Department(departmentId);
        return new Employee(id, fName, lName, email, phoneNumber, hireDate, job, salary, employee, department);
    }

    @Override
    public Optional<Employee> selectById(long id) {
        Employee employee = null;
        var sql = """
                select *
                from employees where employee_id=?;
                """;
        try (var con = DbConfig.instance();
            var stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.execute();
           try( var rs = stmt.getResultSet()) {
               while (rs.next()) {
                   employee = getEmployee(rs);
               }
           }catch (Exception ex){
               ex.printStackTrace();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public boolean updateEmail(String email, long id) {
        boolean update = false;
        var sql = """
                update employees set email=? where employee_id=?;
                """;
        var con = DbConfig.instance();
        try ( var stmt = con.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setLong(2, id);
            var effected = stmt.executeUpdate();
            if (effected > 0) {
                update = true;
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
        return update;
    }

    @Override
    public boolean deleteById(long id) {
        boolean delete = false;
        var sql = """
                delete from employees where employee_id=?;
                """;
        var con = DbConfig.instance();
        try (var stmt = con.prepareStatement(sql)){
            stmt.setLong(1, id);
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

    @Override
    public List<Employee> allEmployee() {
        List<Employee> emp = new ArrayList<>();
        var sql = """
                select *
                from employees;
                """;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.execute();
            try(var rs = stmt.getResultSet()) {
                while (rs.next()) {
                    emp.add(getEmployee(rs));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
