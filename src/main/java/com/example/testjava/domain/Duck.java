package com.example.testjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Duck extends Animal {

    public Duck(String id, String name, String numLeg) {
        super(id, name, "DUCK", numLeg);
    }

    @Override
    public void shout() {
        System.out.println("duck shout" + this.getName());
    }

    @Override
    public String toString() {
        return this.getId() + "|" + this.getName() + "|" + this.getType() + "|" + this.getNumLeg();
    }
}
