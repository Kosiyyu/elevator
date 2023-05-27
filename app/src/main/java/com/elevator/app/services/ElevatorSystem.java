package com.elevator.app.services;

import com.elevator.app.models.Elevator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevatorSystem {

    private final ElevatorService elevatorService;

    public ElevatorSystem(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    public void moveUp(Elevator elevator) {
        if (elevator.getCurrentFloor().equals(elevator.getMaxFloor())) {
        //
        } else if (!elevator.getIsRunning()) {
        //
        } else {
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
            elevator.getTargetFloors().remove(elevator.getCurrentFloor());
        }
    }

    public void moveDown(Elevator elevator) {
        if (!elevator.getIsRunning()) {
        //
        } else if (elevator.getCurrentFloor().equals(elevator.getMinFloor())) {
        //
        } else {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            elevator.getTargetFloors().remove(elevator.getCurrentFloor());
        }
    }

    public void addFloors(Elevator elevator, Integer passengerFloor, Integer targetFloor) {
        int elevatorDirection = elevator.getTargetFloors().get(0) - elevator.getCurrentFloor();

        if (!elevator.getIsRunning()) {
        //
        }
        else if (elevatorDirection > 0) {
            elevator.getTargetFloors().add(passengerFloor);
            elevator.getTargetFloors().add(targetFloor);
        } else if (elevatorDirection < 0) {
            elevator.getTargetFloors().add(0, targetFloor);
            elevator.getTargetFloors().add(0, passengerFloor);
        } else {
            elevator.getTargetFloors().add(targetFloor);
        }
    }

    public void start(Elevator elevator) {
        elevator.setIsRunning(true);
    }

    public void stop(Elevator elevator) {
        elevator.setIsRunning(false);
    }



    public Elevator assignElevator(int passengerFloor, int targetFloor) {
        List<Elevator> elevators = elevatorService.getAllElevators();
        if(elevators.isEmpty()){
            throw new IllegalArgumentException("No elevators exist");
        }

        Elevator freeElevator = null;
        int minTraversalFloors = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (!elevator.getIsRunning() && elevator.getTargetFloors().isEmpty()) {
                int distance = Math.abs(elevator.getCurrentFloor() - passengerFloor);
                if (distance == 0) {
                    return elevator;
                }
                if (distance < minTraversalFloors) {
                    minTraversalFloors = distance;
                    freeElevator = elevator;
                }
            }
        }

        Elevator busyElevator = null;
        int passengerDirection = targetFloor - passengerFloor;

        for (Elevator elevator : elevators) {
            if (elevator.getIsRunning()) {
                int elevatorTraversalFloors = calculateTraversalFloors(elevator, passengerFloor, targetFloor);
                int elevatorDirection = getElevatorDirection(elevator);

                if (elevatorTraversalFloors < minTraversalFloors && elevatorDirection * passengerDirection > 0) {
                    minTraversalFloors = elevatorTraversalFloors;
                    busyElevator = elevator;
                }
            }
        }
        if (freeElevator != null && Math.abs(freeElevator.getCurrentFloor() - passengerFloor) < minTraversalFloors) {
            return freeElevator;
        }

        return busyElevator;
    }

    private int calculateTraversalFloors(Elevator elevator, int passengerFloor, int targetFloor) {
        int traversalFloors = Math.abs(elevator.getCurrentFloor() - passengerFloor);

        List<Integer> targetFloors = elevator.getTargetFloors();
        int prevFloor = elevator.getCurrentFloor();

        for (Integer floor : targetFloors) {
            traversalFloors += Math.abs(floor - prevFloor);
            prevFloor = floor;

            if (floor == passengerFloor) {
                break;
            }
        }

        traversalFloors += Math.abs(targetFloor - prevFloor);

        return traversalFloors;
    }

    private int getElevatorDirection(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        List<Integer> targetFloors = elevator.getTargetFloors();

        if (targetFloors.isEmpty()) {
            return 0;
        }
        int firstFloor = targetFloors.get(0);

        if (firstFloor > currentFloor) {
            return 1;
        } else if (firstFloor < currentFloor) {
            return -1;
        } else {
            return 0;
        }
    }

}

