package com.elevator.app.controllers;

import com.elevator.app.models.Elevator;
import com.elevator.app.models.UserRequest;
import com.elevator.app.services.ElevatorManager;
import com.elevator.app.services.ElevatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/elevator")
public class ElevatorController {
    private final ElevatorService elevatorService;
    private final ElevatorManager elevatorManager;

    public ElevatorController(ElevatorService elevatorService, ElevatorManager elevatorManager) {
        this.elevatorService = elevatorService;
        this.elevatorManager = elevatorManager;
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

    @PostMapping("/add/empty")
    public ResponseEntity addEmpty(@RequestBody Elevator elevator){
        try{
            elevatorService.addElevator(elevator);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(elevatorService.getAllElevators());
    }
    @PostMapping("/add/user/request")
    public ResponseEntity addUserRequest(@RequestBody UserRequest userRequest){
        try{
            List<Elevator> elevatorList = elevatorService.getAllElevators();
            elevatorManager.setElevators(elevatorList);
            if(elevatorList.isEmpty()){
                throw new Exception();
            }
            int id = elevatorManager.findOptimal(elevatorManager.getElevators());
            if(id != -1){
                Elevator elevator = elevatorManager.getElevators().get(id);
                elevatorManager.processRequestOutside(elevator, userRequest);
                elevatorService.patchElevator(elevator);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(elevatorService.getAllElevators());
        }
        return ResponseEntity.status(200).body(elevatorService.getAllElevators());
    }

    @PostMapping ("/run/round")
    public ResponseEntity runRound(){
        try{
            List<Elevator> elevatorList = elevatorService.getAllElevators();
            elevatorManager.setElevators(elevatorList);
            if(elevatorList.isEmpty()){
                throw new Exception();
            }
            for(Elevator elevator : elevatorManager.getElevators()){
                elevatorManager.elevatorMove(elevator);
                elevatorService.patchElevator(elevator);
            }

        }
        catch (Exception e){
            return ResponseEntity.status(400).body(elevatorService.getAllElevators());
        }
        return ResponseEntity.status(200).body(elevatorService.getAllElevators());
    }

    @PostMapping ("/run/reset")
    public ResponseEntity runReset(){
        try{
            elevatorService.removeAll();
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(elevatorService.getAllElevators());
        }
        return ResponseEntity.status(200).body(elevatorService.getAllElevators());
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
