package com.klimov.entities.animals.predators;

import com.klimov.entities.animals.Animal;
import com.klimov.entities.animals.herbivore.Sheep;

public class Fox extends Predator {
    public Fox() {
        super(8, 30, 2, 2);
    }

    @Override
    public Animal reproduce() {
        return new Fox();
    }
}
