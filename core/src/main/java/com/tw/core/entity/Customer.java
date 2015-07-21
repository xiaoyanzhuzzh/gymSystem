package com.tw.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;
    private String name;
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
