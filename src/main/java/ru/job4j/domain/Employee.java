package ru.job4j.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private BigInteger itx;

    private Timestamp hiring;
    @Transient
    private List<Person> list = new ArrayList<>();

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    public void addList(Person person) {
        list.add(person);
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigInteger getItx() {
        return itx;
    }

    public void setItx(BigInteger itx) {
        this.itx = itx;
    }

    public Timestamp getHiring() {
        return hiring;
    }

    public void setHiring(Timestamp hiring) {
        this.hiring = hiring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name)
                && Objects.equals(surname, employee.surname)
                && Objects.equals(itx, employee.itx) && Objects.equals(hiring, employee.hiring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, itx, hiring);
    }
}
