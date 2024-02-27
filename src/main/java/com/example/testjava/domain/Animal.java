package com.example.testjava.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public abstract class Animal {
    @Id
    @Column(length = 36)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "numLeg")
    private String numLeg;

    public Animal(String id, String name, String type, String numLeg) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.numLeg = numLeg;
    }

    public abstract void shout();
}
