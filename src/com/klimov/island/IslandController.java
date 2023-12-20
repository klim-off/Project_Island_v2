package com.klimov.island;

import com.klimov.entities.animals.EatingMap;
import com.klimov.simulation.SimulationSettings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IslandController {
    private IslandMap islandMap;
    private EatingMap eatingMap;
    private SimulationSettings simulationSettings;

    public IslandController(IslandMap islandMap, EatingMap eatingMap, SimulationSettings simulationSettings) {
        this.islandMap = islandMap;
        this.eatingMap = eatingMap;
        this.simulationSettings = simulationSettings;
    }
}
