package com.klimov.dialog;

import com.klimov.simulation.SimulationSettings;

import java.util.Scanner;

public class UserDialog {

    private final SimulationSettings simulationSettings;


    public UserDialog(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Hello");
            System.out.println("Current settings:");
            System.out.println("Heigth of Island " + simulationSettings.getHeightMap());
            System.out.println("Width of Island " + simulationSettings.getWidthMap());

            // TODO - add parameters changes
        }
    }
}
