package com.elevator.app.controllers;

import com.elevator.app.models.Elevator;
import com.elevator.app.services.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PatchMapping("/update")
    public ResponseEntity update(@RequestBody Map<String, Integer> requestBody){
        Integer passengerFloor = requestBody.get("passengerFloor");
        Integer targetFloor = requestBody.get("targetFloor");

        try{
            simulationService.update(passengerFloor, targetFloor);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(204).body(null);
    }

    @GetMapping("/run")
    public ResponseEntity runTour(){
        List<Elevator> elevatorList;
        elevatorList = simulationService.runTour();
        try{
            elevatorList = simulationService.runTour();
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(elevatorList);
    }
}
