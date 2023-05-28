package org.example.model;

import lombok.Data;
import org.example.service.ElevatorManager;

@Data
public class Simulation {
//redundant???
    private ElevatorManager elevatorManager;

    public Simulation(int size){
        //add to db
        //then add to elevatorManager

        //for now
        for(int i = 0; i < size; i++){
            elevatorManager.addElevator(new Elevator());
        }
    }

    public Simulation(ElevatorManager elevatorManager){
        this.elevatorManager = elevatorManager;
    }

    public void roundUp(){
        for(Elevator elevator : elevatorManager.getElevators()){
            elevatorManager.elevatorMove(elevator);
        }
    }



}
