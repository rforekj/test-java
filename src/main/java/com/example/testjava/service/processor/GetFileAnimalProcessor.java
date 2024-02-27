package com.example.testjava.service.processor;

import com.example.testjava.domain.Animal;
import com.example.testjava.domain.Cat;
import com.example.testjava.domain.Duck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class GetFileAnimalProcessor extends Thread {
    private final List<Animal> animalList;

    public GetFileAnimalProcessor(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader("animal.txt"))) {
            String line = br.readLine();

            while (line != null) {
                String[] info = line.split("\\|");
                if (info[2].equals("DUCK")) {
                    animalList.add(new Duck(info[0], info[1], info[3]));
                }
                if (info[2].equals("CAT")) {
                    animalList.add(new Cat(info[0], info[1], info[3]));
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
