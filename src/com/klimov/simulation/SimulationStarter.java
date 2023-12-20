package com.klimov.simulation;

import com.klimov.dialog.UserDialog;
import com.klimov.entities.animals.Action;
import com.klimov.entities.animals.Animal;
import com.klimov.entities.animals.Direction;
import com.klimov.island.IslandController;
import com.klimov.island.IslandMap;
import com.klimov.island.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationStarter {

    private final IslandMap islandMap;
    private final UserDialog userDialog;
    private final SimulationSettings simulationSettings;
    private final IslandController islandController;

    public SimulationStarter() {
        this.simulationSettings = new SimulationSettings();
        this.userDialog = new UserDialog(simulationSettings);
        this.islandMap = new IslandMap(simulationSettings.getHeightMap(), simulationSettings.getWidthMap());
        this.islandController = new IslandController(islandMap, null, simulationSettings);
    }

    public void start() {
        islandController.getIslandMap().initialize();
        islandController.getIslandMap().fill(simulationSettings.getMaxEntityCountOnLocation());

        for (int i = 0; i < 100; i++) { // days in island

            for (int coordY = 0; coordY < islandMap.getHeight(); coordY++) {   //loop on X
                for (int coordX = 0; coordX < islandMap.getWeight(); coordX++) { // loop on Y
                    Location location = islandMap.getLocations()[coordY][coordX];
                    List<Animal> animals = new ArrayList<>(location.getAnimals());
                    for (Animal animal : animals) {  // loop on animal
                        if (isDead(animal)) {
                            location.removeEntity(animal);
                            continue;
                        }
                        Action action = animal.chooseAction();
                        doAction(action, animal, location);
                    }
                }
            }
        }
    }

    private void doAction(Action action, Animal animal, Location location) {
        switch (action) {
            case MOVE -> doMove(animal, location);
            //       case EAT -> doEat(animal,location);
            //       case REPRODUCE -> doReproduce(animal,location);
            //       case SLEEP -> doSleep(animal,location);
        }
        reduceHelth(animal);
    }

    private void doMove(Animal animal, Location location) {
        int stepsCount = ThreadLocalRandom.current().nextInt(animal.getSpeed() + 1);
        while (stepsCount > 0) {
            Direction direction = animal.chooseDirection();
            switch (direction) {
                case UP -> location = stepUp(animal, location);
                case DOWN -> location = stepDown(animal, location);
                case LEFT -> location = stepLeft(animal, location);
                case RIGHT -> location = stepRight(animal, location);
            }
            stepsCount--;
        }

    }

    private Location stepDown(Animal animal, Location curentLocation) {
        int currentX = curentLocation.getCoordX();
        int currenY = curentLocation.getCoordY();

        if (currenY < islandMap.getHeight() - 1) {
            Location newLocation = islandMap.getLocations()[currenY + 1][currentX];

            if (cantStep(animal, newLocation)) {
                return curentLocation;
            }
            newLocation.addEntity(animal);
            curentLocation.removeEntity(animal);
            return newLocation;
        }
        return curentLocation;
    }

    private Location stepUp(Animal animal, Location curentLocation) {
        int currentX = curentLocation.getCoordX();
        int currenY = curentLocation.getCoordY();

        if (currenY > 0) {
            Location newLocation = islandMap.getLocations()[currenY - 1][currentX];

            if (cantStep(animal, newLocation)) {
                return curentLocation;
            }
            newLocation.addEntity(animal);
            curentLocation.removeEntity(animal);
            return newLocation;
        }
        return curentLocation;
    }

    private Location stepRight(Animal animal, Location curentLocation) {
        int currentX = curentLocation.getCoordX();
        int currenY = curentLocation.getCoordY();

        if (currentX < islandMap.getWeight() - 1) {
            Location newLocation = islandMap.getLocations()[currenY][currentX + 1];
            if (cantStep(animal, newLocation)) {
                return curentLocation;
            }
            newLocation.addEntity(animal);
            curentLocation.removeEntity(animal);
            return newLocation;
        }
        return curentLocation;
    }

    private Location stepLeft(Animal animal, Location curentLocation) {
        int currentX = curentLocation.getCoordX();
        int currenY = curentLocation.getCoordY();

        if (currentX > 0) {
            Location newLocation = islandMap.getLocations()[currenY][currentX - 1];
            if (cantStep(animal, newLocation)) {
                return curentLocation;
            }
            newLocation.addEntity(animal);
            curentLocation.removeEntity(animal);
            return newLocation;
        }
        return curentLocation;
    }

    private boolean cantStep(Animal animal, Location newLocation) {
        String animalAsString = animal.getClass().getSimpleName();
        return newLocation.getEntitiesCount().get(animalAsString) >= animal.getMaxOnCage();
    }

    private void reduceHelth(Animal animal) {
        Double healthScale = animal.getHealthScale()
                - ((animal.getEnoughAmountOfFood() * simulationSettings.getReduceHealthPercent) / 100);
        animal.setHealthScale(healthScale);
    }

    private boolean isDead(Animal animal) {
        return animal.getHealthScale() <= 0;
    }
}
