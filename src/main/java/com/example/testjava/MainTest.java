package com.example.testjava;

import com.example.testjava.domain.Animal;
import com.example.testjava.domain.Cat;
import com.example.testjava.domain.Duck;
import com.example.testjava.service.AnimalService;
import com.example.testjava.service.processor.SaveDBAnimalProcessor;
import com.example.testjava.service.processor.SaveFileAnimalProcessor;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@AllArgsConstructor
public class MainTest implements CommandLineRunner {
    private final AnimalService animalService;

    @Override
    public void run(String... args) throws Exception {
        List<Animal> animals = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            int randomNum = random.nextInt();
            String id = UUID.randomUUID().toString();
            if (randomNum % 2 == 0) {
                animals.add(new Cat(id, "CAT " + i, "2"));
            } else {
                animals.add(new Duck(id, "DUCK " + i, "2"));
            }
        }
        SaveDBAnimalProcessor saveDBAnimalProcessor = new SaveDBAnimalProcessor(animalService, animals);
        saveDBAnimalProcessor.start();
        SaveFileAnimalProcessor saveFileAnimalProcessor = new SaveFileAnimalProcessor(animals);
        saveFileAnimalProcessor.start();
    }
}
