package com.company.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Date hiraDate;
    private Job job;
    private BigDecimal salary;
    private Employee manager;
    private Department department;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, String email,
                    String phoneNumber, Date hiraDate, Job job,
                    BigDecimal salary, Employee manager, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hiraDate = hiraDate;
        this.job = job;
        this.salary = salary;
        this.manager = manager;
        this.department = department;
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHiraDate() {
        return hiraDate;
    }

    public void setHiraDate(Date hiraDate) {
        this.hiraDate = hiraDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hiraDate=" + hiraDate +
                ", job=" + job +
                ", salary=" + salary +
                ", manager=" + manager +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(email, employee.email) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(hiraDate, employee.hiraDate) && Objects.equals(job, employee.job) && Objects.equals(salary, employee.salary) && Objects.equals(manager, employee.manager) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumber, hiraDate, job, salary, manager, department);
    }
}
