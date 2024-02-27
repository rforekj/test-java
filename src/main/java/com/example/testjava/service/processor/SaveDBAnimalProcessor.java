package com.example.testjava.service.processor;

import com.example.testjava.domain.Animal;
import com.example.testjava.service.AnimalService;

import java.util.List;

public class SaveDBAnimalProcessor extends Thread {
    private final AnimalService animalService;
    private final List<Animal> animalList;

    public SaveDBAnimalProcessor(AnimalService animalService, List<Animal> animalList) {
        this.animalService = animalService;
        this.animalList = animalList;
    }

    public void run() {
        animalService.saveAnimal(animalList);
    }
}
