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
        elevatorRepository.save(elevator);
    }

    public void patchElevator(Elevator elevator) {
        Elevator existingElevator = elevatorRepository.findById(elevator.getId()).orElse(null);
        if (existingElevator == null) {
            throw new IllegalArgumentException("Elevator does not exist");
        } else {
            if (elevator.getFloorsQueue() != null && !elevator.getFloorsQueue().isEmpty()) {
                existingElevator.setFloorsQueue(elevator.getFloorsQueue());
            }
            existingElevator.setCurrentFloor(elevator.getCurrentFloor());
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
