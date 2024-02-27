package com.example.testjava.service.processor;

import com.example.testjava.domain.Animal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class SaveFileAnimalProcessor extends Thread {
    private final List<Animal> animalList;

    public SaveFileAnimalProcessor(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void run() {
        List<String> lines = animalList.stream().map(Animal::toString).collect(Collectors.toList());
        Path file = Paths.get("animal.txt");
        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
