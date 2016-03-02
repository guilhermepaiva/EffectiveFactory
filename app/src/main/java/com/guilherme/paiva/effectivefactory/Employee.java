package com.guilherme.paiva.effectivefactory;

/**
 * Created by guilhermepaiva on 13/02/16.
 */
public class Employee {
    int id;
    String name;
    String supervisor;
    String cell;
    String phone;

    public Employee() {}

    public Employee(int id, String name, String supervisor, String cell, String phone) {
        this.id = id;
        this.name = name;
        this.supervisor = supervisor;
        this.cell = cell;
        this.phone = phone;
    }

    public Employee(String name, String supervisor, String cell, String phone) {
        this.name = name;
        this.supervisor = supervisor;
        this.cell = cell;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
