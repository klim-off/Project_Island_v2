package com.klimov.island;

import com.klimov.entities.Entity;
import com.klimov.entities.EntitiesFactory;
import com.klimov.entities.EntityType;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;
@Getter
@Setter
public class IslandMap {
    private final int height;
    private final int weight;
    private  Location[][] locations;
    private final EntitiesFactory entitiesFactory;

    public IslandMap(int height, int weight) {
        this.height = height;
        this.weight = weight;
        this.locations = new Location[height][weight];
        this.entitiesFactory = new EntitiesFactory();
        entitiesFactory.initEntitiesMap();

    }
    public void initialize (){
        for (int coordY = 0; coordY < height; coordY++) {
            for (int coordX = 0; coordX < weight; coordX++) {
                locations[coordY][coordX] = new Location(coordX,coordY);
            }
        }
    }

    public void fill (int maxEntityCount){
        for (int coordY = 0; coordY < height; coordY++) {
            for (int coordX = 0; coordX < weight; coordX++) {
                for (int i = 0; i < maxEntityCount; i++) {
                    Entity entity = getRandomEntity();

                    var entityAsString = entity.getClass().getSimpleName();
                    var entityCountOnLocation = locations[coordY][coordX]
                            .getEntitiesCount().getOrDefault(entityAsString,0);
                    if (entityCountOnLocation >= entity.getMaxOnCage()) {continue;}
                    locations[coordY][coordX].addEntity(entity);
                }
            }
        }
    }

    private Entity getRandomEntity() {
        EntityType[] entityTypes = EntityType.values();
        var size = entityTypes.length;
        EntityType entityType = entityTypes[ThreadLocalRandom.current().nextInt(size)];
        return entitiesFactory.createEntity(entityType);
    }
}
