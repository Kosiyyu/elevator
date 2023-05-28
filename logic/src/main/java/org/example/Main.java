package org.example;

import org.example.model.Elevator;
import org.example.model.Simulation;
import org.example.model.UserRequest;
import org.example.service.ElevatorManager;

import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
//        ElevatorManager elevatorManager = new ElevatorManager();
//
//        elevatorManager.addElevator(new Elevator());
        //1 1 OK
//        elevatorManager.getElevators().get(0).setCurrentFloor(0);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(1);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0), 6,1);

        //1 -1 OK
//        elevatorManager.getElevators().get(0).setCurrentFloor(0);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(4);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(5);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0),6,-1);

        //-1 -1 OK
//        elevatorManager.getElevators().get(0).setCurrentFloor(5);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(4);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(1);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0),6,-1);

        //-1 1 OK
//        elevatorManager.getElevators().get(0).setCurrentFloor(6);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(3);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(2);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(4);
//        elevatorManager.getElevators().get(0).getFloorsQueue().add(5);
//        elevatorManager.addFloors(elevatorManager.getElevators().get(0),6,1);

//        System.out.println(elevatorManager.getElevators().get(0));
//        System.out.println(elevatorManager.calculatePath(elevatorManager.getElevators().get(0)));

        ElevatorManager elevatorManager = new ElevatorManager();
        Elevator elevator1 = new Elevator();
        elevator1.setCurrentFloor(3);
        elevator1.getFloorsQueue().add(2);
        elevator1.getFloorsQueue().add(4);
        elevator1.getFloorsQueue().add(6);
        elevatorManager.addElevator(elevator1);

        //
        Elevator elevator2 = new Elevator();
        elevator2.setCurrentFloor(3);
        elevator2.getFloorsQueue().add(1);
        elevator2.getFloorsQueue().add(2);
        elevator2.getFloorsQueue().add(3);
        elevator2.getFloorsQueue().add(5);
        elevatorManager.addElevator(elevator2);
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));
//        elevatorManager.elevatorMove(elevatorManager.getElevators().get(1));

        Elevator elevator3 = new Elevator();
        elevator3.setCurrentFloor(3);
        elevator3.getFloorsQueue().add(1);
        elevator3.getFloorsQueue().add(2);
        elevator3.getFloorsQueue().add(3);
        elevator3.getFloorsQueue().add(5);
        elevator3.getFloorsQueue().add(6);
        elevator3.getFloorsQueue().add(5);
        elevatorManager.addElevator(elevator3);


        elevatorManager.listOutElevators();

        System.out.println("1212312 " + elevatorManager.findOptimal(elevatorManager.getElevators()));
        int id = elevatorManager.findOptimal(elevatorManager.getElevators());
        if(id != -1){
            Elevator elevator = elevatorManager.getElevators().get(id);
            elevatorManager.processRequestOutside(elevator, new UserRequest(-1, 3));
        }

        elevatorManager.listOutElevators();

//        Simulation simulation = new Simulation(elevatorManager);
//        simulation.

















    }
}