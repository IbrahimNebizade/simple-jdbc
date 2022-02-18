package com.company.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Job {
    private Long id;
    private String title;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    public Job() {
    }

    public Job(Long id) {
        this.id = id;
    }

    public Job(Long id, String title, BigDecimal minSalary, BigDecimal maxSalary) {
        this.id = id;
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(title, job.title) &&
                Objects.equals(minSalary, job.minSalary) &&
                Objects.equals(maxSalary, job.maxSalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, minSalary, maxSalary);
    }
}
