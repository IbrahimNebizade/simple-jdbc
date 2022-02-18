package com.company.repository.Impl;

import com.company.config.DbConfig;
import com.company.domain.Job;
import com.company.repository.Inter.JobRepositoryInter;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepositoryImpl implements JobRepositoryInter {
    private Job getJob(ResultSet rs) throws Exception {
        var id = rs.getLong("job_id");
        var title = rs.getString("job_title");
        var minSalary = rs.getBigDecimal("min_salary");
        var maxSalary = rs.getBigDecimal("max_salary");
        return new Job(id, title, minSalary, maxSalary);
    }

    @Override
    public List<Job> allJob() {
        List<Job> jobs = new ArrayList<>();
        var sql = """
                select *
                from jobs;
                """;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                jobs.add(getJob(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public Optional<Job> selectJobByTitle(String title) {
        var sql = """
                select * from jobs where job_title=?;
                """;
        Job job = null;
        try (var con = DbConfig.instance()) {
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.execute();
            var rs = stmt.getResultSet();
            while (rs.next()) {
                job = getJob(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(job);
    }

    @Override
    public boolean updateJobMinMaxSalary(String title, BigDecimal minSalary, BigDecimal maxSalary) {
        boolean update = false;
        var sql = """
                update jobs set min_salary=?,max_salary=? where job_title=?;
                """;
        var con = DbConfig.instance();
        try {
            var stmt = con.prepareStatement(sql);
            stmt.setBigDecimal(1, minSalary);
            stmt.setBigDecimal(2, maxSalary);
            stmt.setString(3, title);
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
                delete from jobs where job_id=?;
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
            } catch (Exception exception) {
                e.printStackTrace();
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
