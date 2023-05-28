package com.elevator.app.services;

import com.elevator.app.models.Elevator;
import com.elevator.app.repositories.ElevatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevatorService {
    private final ElevatorRepository elevatorRepository;

    public ElevatorService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public void addElevator(Elevator elevator) {
        //elevator.getTargetFloors().sort(Integer::compareTo);
        elevatorRepository.save(elevator);
    }

    public void patchElevator(Elevator elevator) {
        Elevator existingElevator  = elevatorRepository.findById(elevator.getId()).orElse(null);
        if(existingElevator == null){
            throw new IllegalArgumentException("Elevator does not exist");
        }
        else
        {
            if(elevator.getMinFloor() != null){
                existingElevator.setMinFloor(elevator.getMinFloor());
            }
            if(elevator.getMaxFloor() != null){
                existingElevator.setMaxFloor(elevator.getMaxFloor());
            }
            if(elevator.getCurrentFloor() != null){
                existingElevator.setCurrentFloor(elevator.getCurrentFloor());
            }
            if(elevator.getIsRunning() != null){
                existingElevator.setIsRunning(elevator.getIsRunning());
            }
            if(elevator.getTargetFloors() != null){
                existingElevator.setTargetFloors(elevator.getTargetFloors());
            }
            elevatorRepository.save(existingElevator);
        }
    }

    public void removeElevator(int id) {
        elevatorRepository.deleteById(id);
    }

    public Elevator getElevator(int id) {
        return elevatorRepository.findById(id).orElse(null);
    }

    public List<Elevator> getAllElevators() {
        return elevatorRepository.findAll();
    }

}
