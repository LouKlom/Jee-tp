package org.eclipse.jakarta.model;

import jakarta.persistence.*;

@Entity
@Table
@NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p")
public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    private String number;



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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
