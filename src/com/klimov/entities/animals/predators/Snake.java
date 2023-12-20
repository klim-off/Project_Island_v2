package com.klimov.entities.animals.predators;

import com.klimov.entities.animals.Animal;
import com.klimov.entities.animals.herbivore.Sheep;

public class Snake extends Predator{
    public Snake() {
        super(15, 30, 1, 3);
    }

    @Override
    public Animal reproduce() {
        return new Snake();
    }
}
