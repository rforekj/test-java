package com.example.testjava.service;

import com.example.testjava.domain.Animal;

import java.util.List;

public interface AnimalService {

    void saveAnimal(List<Animal> animals);

    List<Animal> findAll();

    void clear();
}
