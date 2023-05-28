package com.elevator.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Elevator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //limits------------------------------------------

    @ElementCollection
    private final List<Integer> floorsLimit = List.of(0, 6);
    //------------------------------------------------

    //floors------------------------------------------
    @ElementCollection
    private List<Integer> floorsQueue;
    private int currentFloor;
    //------------------------------------------------

    public Elevator(){
        this.floorsQueue = new LinkedList<>();
        this.currentFloor = 0;//watch out for limits
    }



    @Override
    public String toString() {
        return "Elevator{" +
                "floorsLimit=" + floorsLimit +
                ", floorsQueue=" + floorsQueue +
                ", currentFloor=" + currentFloor +
                '}';
    }
}
