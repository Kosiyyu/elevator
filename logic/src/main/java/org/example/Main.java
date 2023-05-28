package org.example;

import org.example.model.Elevator;
import org.example.service.ElevatorManager;

import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        ElevatorManager elevatorManager = new ElevatorManager();

        elevatorManager.addElevator(new Elevator());
        //1 1
//        elevatorManager.getElevators().get(0).setCurrentFloor(0);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(3);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(4);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0),5,1);

        //1 -1
//        elevatorManager.getElevators().get(0).setCurrentFloor(0);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(3);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(6);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(5);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(4);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(3);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0),5,-1);

        System.out.println(elevatorManager.getElevators().get(0));
    }
}