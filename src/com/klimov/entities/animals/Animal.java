package com.klimov.entities.animals;

import com.klimov.annotations.Property;
import com.klimov.entities.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Animal extends Entity {
    private static final int MAX_CHANCE = 100;

    @Property(propertyName = "weight",priority = 1)
    private double weight;
    @Property(propertyName = "maxOnCage",priority = 2)
    private int maxOnCage;
    @Property(propertyName = "speed",defaultValue = "1",priority = 3)
    private int speed;
    @Property(propertyName = "enoughAmountOfFood",priority = 4)
    private double enoughAmountOfFood;
    @Property(propertyName = "unicode",priority = 5)
    private String unicode;
    private double healthScale;

       protected Animal(double weight, int maxOnCage, int speed, double enoughAmountOfFood) {

        this.weight = weight;
        this.maxOnCage = maxOnCage;
        this.speed = speed;
        this.enoughAmountOfFood = enoughAmountOfFood;
        this.unicode = unicode;
        this.healthScale = enoughAmountOfFood;
    }

    public Animal(double weight, int maxOnCage, int speed, double enoughAmountOfFood, String unicode) {
        this.weight = weight;
        this.maxOnCage = maxOnCage;
        this.speed = speed;
        this.enoughAmountOfFood = enoughAmountOfFood;
        this.unicode = unicode;
       }

    public abstract Animal reproduce();

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getMaxOnCage() {
        return maxOnCage;
    }

    public void eat(Entity food) {
        if (food.getWeight() >= this.getWeight()) {
            this.setHealthScale(this.getEnoughAmountOfFood());
        } else {
            double hungerAfterEating = this.getHealthScale() + this.getWeight();
            this.setHealthScale(hungerAfterEating);
        }
    }

    public Action chooseAction() {
        var action = Action.values()[ThreadLocalRandom.current().nextInt(Action.values().length)];
        boolean isActiveAction = ThreadLocalRandom.current().nextInt(MAX_CHANCE) < action.getActionChanceIndex();
        return isActiveAction ? action : Action.SLEEP;

      /* if (isActiveAction) {
            return action;
        }else {
           return Action.SLEEP;
       }*/
    }

    public Direction chooseDirection() {
        //  return Direction.values()[new Random().nextInt(Direction.values().length)];
        return Direction.values()[ThreadLocalRandom.current()
                .nextInt(Direction.values().length)];
    }
}
