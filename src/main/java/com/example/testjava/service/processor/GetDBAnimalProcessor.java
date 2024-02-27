package com.example.testjava.service.processor;

import com.example.testjava.domain.Animal;
import com.example.testjava.service.AnimalService;

import java.util.List;

public class GetDBAnimalProcessor extends Thread {
    private final AnimalService animalService;
    private final List<Animal> animalList;

    public GetDBAnimalProcessor(AnimalService animalService, List<Animal> animalList) {
        this.animalService = animalService;
        this.animalList = animalList;
    }

    public void run() {
        this.animalList.addAll(animalService.findAll());
    }
}
