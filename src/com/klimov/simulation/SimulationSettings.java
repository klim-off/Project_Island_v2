package com.klimov.simulation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationSettings {
    public double getReduceHealthPercent = 30;
    private int widthMap = 100;
    private  int heightMap = 20;
    private int maxEntityCountOnLocation = 100;
    private int simulationCycles = 100;
}
