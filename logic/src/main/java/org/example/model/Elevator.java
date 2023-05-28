package org.example.model;

import lombok.Data;

import java.util.*;

@Data
public class Elevator {

    //limits------------------------------------------
    private final List<Integer> floorsLimit = List.of(0, 6);
    //------------------------------------------------

    //floors------------------------------------------
    private final List<Integer> floorsQueue;
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
