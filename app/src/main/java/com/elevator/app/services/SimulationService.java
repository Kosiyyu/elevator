package com.elevator.app.services;

import com.elevator.app.models.Elevator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {
    private final ElevatorSystem elevatorSystem;

    private final ElevatorService elevatorService;

    public SimulationService(ElevatorSystem elevatorSystem, ElevatorService elevatorService) {
        this.elevatorSystem = elevatorSystem;
        this.elevatorService = elevatorService;
    }

    public List<Elevator> runTour(){
        List<Elevator> elevatorList = elevatorService.getAllElevators();
        if(elevatorList.isEmpty()){
            throw new RuntimeException("No elevators found");
        }
        if(elevatorList == null){
            throw new RuntimeException("No elevators found");
        }
        for (Elevator elevator : elevatorList) {
            if(elevator.getTargetFloors() == null){
                throw new RuntimeException("No elevators found2");
            }
            if (elevator.getTargetFloors().isEmpty()) {
                continue;
            }

            int currentFloor = elevator.getCurrentFloor();
            int nextFloor = elevator.getTargetFloors().get(0);

            if (nextFloor > currentFloor) {
                elevatorSystem.moveUp(elevator);
            } else if (nextFloor < currentFloor) {
                elevatorSystem.moveDown(elevator);
            }
            elevatorService.patchElevator(elevator);
        }
        return elevatorList;
    }

    public void update(int passengerFloor, int targetFloor){
        Elevator elevator = elevatorSystem.assignElevator(passengerFloor, targetFloor);
        elevatorSystem.addFloors(elevator, passengerFloor, targetFloor);
        //elevator.getTargetFloors().sort(Integer::compareTo); //bug
        elevatorService.patchElevator(elevator);
    }


}
