package com.klimov.entities.animals.predators;

import com.klimov.annotations.Entity;
import com.klimov.entities.animals.Animal;
import com.klimov.entities.animals.herbivore.Sheep;
@Entity(className = "wolf")
public class Wolf extends Predator{


    public Wolf(Double weight, Integer maxOnCage, Integer speed, Double enoughAmountOfFood) {
        super(weight, maxOnCage, speed, enoughAmountOfFood);
    }

    public Wolf(Double weight, Integer maxOnCage, Integer speed, Double enoughAmountOfFood, String unicode) {
        super(weight, maxOnCage, speed, enoughAmountOfFood, unicode);
    }

    @Override
    public Animal reproduce() {
        return null;
        //return new Wolf();
    }
}
