package com.klimov.entities.animals.herbivore;

import com.klimov.entities.Entity;
import com.klimov.entities.animals.Animal;


public abstract class Herbivore extends Animal {
    protected Herbivore(double weight, int maxOnCage, int speed, double enoughAmountOfFood) {
        super(weight, maxOnCage, speed, enoughAmountOfFood);
    }

    protected Herbivore(double weight, int maxOnCage, int speed, double enoughAmountOfFood, String unicode) {
        super(weight, maxOnCage, speed, enoughAmountOfFood, unicode);
    }

    @Override
    public void eat(Entity food) {
        if (food.getWeight() >= this.getWeight()){
            this.setHealthScale(this.getEnoughAmountOfFood());
        }else{
            double hungerAfterEating = this.getHealthScale() + this.getWeight();
            this.setHealthScale(hungerAfterEating);
        }

    }
}
