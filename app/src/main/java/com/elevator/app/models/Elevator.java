package com.elevator.app.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Elevator {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Integer id;

    private Integer currentFloor;

    @ElementCollection
    private List<Integer> targetFloors;

    private Boolean isRunning;

    private Integer minFloor;

    private Integer maxFloor;


    public Elevator() {}

    public Elevator(Integer id) {
        this.id = id;
        this.currentFloor = 0;
        this.targetFloors = new ArrayList<>();
        this.isRunning = true;
    }

    public Elevator(Integer id, Integer currentFloor, List<Integer> targetFloors, boolean isRunning) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.targetFloors = targetFloors;
        this.isRunning = isRunning;
    }
}
