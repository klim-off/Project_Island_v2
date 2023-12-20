package com.klimov.island;

import com.klimov.entities.Entity;
import com.klimov.entities.animals.Animal;
import com.klimov.entities.plants.Plant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
public class Location {
    private int coordX;
    private int coordY;
    private List<Entity> entities;
    private Map<String,Integer> entitiesCount;

    public Location(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.entities = new ArrayList<>();
        this.entitiesCount = new HashMap<>();
    }

    public void addEntity (Entity entity){
        entities.add(entity);
        addToStatistic(entity);
    }

       public void removeEntity(Entity entity){
        entities.remove(entity);
        removeFromStatistic(entity);
    }

    public List<Animal> getAnimals (){
        return entities.stream()
                .filter(entity -> entity instanceof Animal)
                .map(entity -> (Animal) entity)
                .toList();
    }
    public List<Plant> getPlants (){
         return  entities.stream()
                 .filter(entity -> entity instanceof Plant)
                 .map(entity -> (Plant) entity)
                 .toList();
    }

    private void addToStatistic(Entity entity) {
        var entitiesAsString = getEntityName(entity);
        entitiesCount.merge(entitiesAsString,1,(oldValue,newValue)->oldValue+1);
    }

    private static String getEntityName(Entity entity) {
        return entity.getClass().getSimpleName();
    }

    private void removeFromStatistic (Entity entity){
        var entityString = getEntityName(entity);
        entitiesCount.merge(entityString,1,(oldValue,newValue)->{
         int newCount = oldValue -1;
         if (newCount <=0) {return null;}
         else {return newCount;}
        });

    }
}
