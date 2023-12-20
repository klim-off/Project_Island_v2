package com.klimov.entities.animals.herbivore;

import com.klimov.annotations.Entity;
import com.klimov.entities.animals.Animal;
@Entity(className = "horse")
public class Horse extends Herbivore {


    public Horse(Double weight, Integer maxOnCage, Integer speed, Double enoughAmountOfFood) {
        super(weight, maxOnCage, speed, enoughAmountOfFood);
    }

    public Horse(Double weight, Integer maxOnCage, Integer speed, Double enoughAmountOfFood, String unicode) {
        super(weight, maxOnCage, speed, enoughAmountOfFood, unicode);
    }

    @Override
    public Animal reproduce() {
        return null;
       // return new Horse();
    }
}
