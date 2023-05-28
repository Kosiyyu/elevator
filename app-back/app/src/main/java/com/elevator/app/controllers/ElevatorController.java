package com.elevator.app.controllers;

import com.elevator.app.models.Elevator;
import com.elevator.app.services.ElevatorService;
import jakarta.persistence.EntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Elevator>> getAll(){
        List list;
        try{
            list = elevatorService.getAllElevators();
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("one")
    public ResponseEntity get(@RequestBody Map<String, Integer> requestBody){
        Integer id = requestBody.get("id");
        Elevator elevator;
        try{
            elevator = elevatorService.getElevator(id);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(elevator);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Elevator elevator){
        try{
            elevatorService.addElevator(elevator);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(201).body(null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Map<String, Integer> requestBody){
        Integer id = requestBody.get("id");
        try{
            elevatorService.removeElevator(id);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(204).body(null);
    }

    @PatchMapping("/edit")
    public ResponseEntity edit(@RequestBody Elevator elevator){
        try{
            elevatorService.patchElevator(elevator);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(204).body(null);
    }
}
