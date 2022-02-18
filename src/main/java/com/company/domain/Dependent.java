package com.company.domain;

import java.util.Objects;

public class Dependent {
    private Long id;
    private String firstName;
    private String lastName;
    private String relationShip;
    private Employee employee;

    public Dependent() {
    }

    public Dependent(Long id, String firstName, String lastName, String relationShip, Employee employee) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationShip = relationShip;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependent dependent = (Dependent) o;
        return Objects.equals(id, dependent.id) && Objects.equals(firstName, dependent.firstName) && Objects.equals(lastName, dependent.lastName) && Objects.equals(relationShip, dependent.relationShip) && Objects.equals(employee, dependent.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, relationShip, employee);
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", relationShip='" + relationShip + '\'' +
                ", employee=" + employee +
                '}';
    }
}
