package com.example.testjava.service.impl;

import com.example.testjava.domain.Animal;
import com.example.testjava.repository.AnimalRepository;
import com.example.testjava.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    @Override
    public void saveAnimal(List<Animal> animals) {
        animalRepository.saveAll(animals);
    }
}
