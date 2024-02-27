package com.example.testjava;

import com.example.testjava.domain.Animal;
import com.example.testjava.domain.Cat;
import com.example.testjava.domain.Duck;
import com.example.testjava.service.AnimalService;
import com.example.testjava.service.processor.GetDBAnimalProcessor;
import com.example.testjava.service.processor.GetFileAnimalProcessor;
import com.example.testjava.service.processor.SaveDBAnimalProcessor;
import com.example.testjava.service.processor.SaveFileAnimalProcessor;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class MainTest implements CommandLineRunner {
    private final AnimalService animalService;

    @Override
    public void run(String... args) throws Exception {
        animalService.clear();
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
        saveFileAnimalProcessor.join();
        saveDBAnimalProcessor.join();

        System.out.println("DONE");

        List<Animal> listFromDb = new ArrayList<>();
        List<Animal> listFromFile = new ArrayList<>();
        GetDBAnimalProcessor getDBAnimalProcessor = new GetDBAnimalProcessor(animalService, listFromDb);
        GetFileAnimalProcessor getFileAnimalProcessor = new GetFileAnimalProcessor(listFromFile);
        getDBAnimalProcessor.start();
        getFileAnimalProcessor.start();
        getDBAnimalProcessor.join();
        getFileAnimalProcessor.join();

        listFromDb.sort(Comparator.comparing(Animal::getId));
        listFromFile.sort(Comparator.comparing(Animal::getId));

        for (int i = 0; i < listFromFile.size(); i++) {
            Animal animalFile = listFromFile.get(i);
            Animal animalDb = listFromDb.get(i);
            if (!Objects.equals(animalDb, animalFile)) {
                System.out.println("Not same");
                return;
            }
        }
        System.out.println("SAME");
    }
}
