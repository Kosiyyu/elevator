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

    //state-------------------------------------------
    private boolean movingUp;
    private boolean movingDown;
    //------------------------------------------------

    public Elevator(){
        this.floorsQueue = new LinkedList<>();
        this.currentFloor = 0;//watch out for limits
        this.movingUp = false;
        this.movingDown = false;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "floorsLimit=" + floorsLimit +
                ", floorsQueue=" + floorsQueue +
                ", currentFloor=" + currentFloor +
                ", movingUp=" + movingUp +
                ", movingDown=" + movingDown +
                '}';
    }
}
