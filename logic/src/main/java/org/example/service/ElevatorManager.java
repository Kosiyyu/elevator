package org.example.service;

import lombok.Data;
import org.example.model.Elevator;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElevatorManager {

    //content------------------------
    private final List<Elevator> elevators;
    //-------------------------------

    public ElevatorManager() {
        this.elevators = new ArrayList<>();
    }

    public boolean addElevator(Elevator elevator) {
        return elevators.add(elevator);
    }

    public boolean removeElevator(Elevator elevator) {
        return elevators.remove(elevator);
    }

    public boolean moveUp(Elevator elevator) {
        if (elevator.getCurrentFloor() + 1 <= elevator.getFloorsLimit().get(1)) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
            if (elevator.isMovingDown()) {
                elevator.setMovingDown(false);
            }
            elevator.setMovingDown(true);
            return true;
        }
        return false;
    }

    public boolean moveDown(Elevator elevator) {
        if (elevator.getCurrentFloor() - 1 >= elevator.getFloorsLimit().get(0)) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            if (elevator.isMovingUp()) {
                elevator.setMovingUp(false);
            }
            elevator.setMovingDown(true);
            return true;
        }
        return false;
    }

    //request value -> user direction
    //request value can be -1 or 1
    public boolean addFloors(Elevator elevator, int staringFloor, int requestValue) {
        if (!(staringFloor >= elevator.getFloorsLimit().get(0) && staringFloor <= elevator.getFloorsLimit().get(1))) {
            return false;
        } else if (!(requestValue == 1 || requestValue == -1)) {
            return false;
        }

        int elevatorDirecton = elevatorDirection(elevator);

        //if elevator goes up and user want to go up as well
        if (elevatorDirecton == 1 && requestValue == 1) {
            System.out.println("1 1");
            //if elevator is under staringFloor
            //check if staringFloor is already in list
            if(elevator.getFloorsQueue().contains(staringFloor)){
                return true;
            }
            //check if staringFloor is already in list
            else if(!elevator.getFloorsQueue().contains(staringFloor)){
                int flag = elevator.getCurrentFloor();
                for (Integer i : elevator.getFloorsQueue()) {
                    if(i >= flag && flag <= staringFloor){
                        flag = i;
                    }
                }
                int id = elevator.getFloorsQueue().indexOf(flag);
                id = id == 0 ? id : elevator.getFloorsQueue().indexOf(flag) + 1;
                elevator.getFloorsQueue().add(id, staringFloor);
                return true;
            }
        }
        //if elevator goes up and user want to go down
        else if (elevatorDirecton == 1 && requestValue == -1) {
            System.out.println("1 -1");
            int reversingIndex = findFirstReversingIndex(elevator.getFloorsQueue());
            if(elevator.getFloorsQueue().contains(staringFloor) && reversingIndex != -1 && elevator.getFloorsQueue().lastIndexOf(staringFloor) >= reversingIndex){
                return true;
            }
            else if(reversingIndex == -1){
                elevator.getFloorsQueue().add(staringFloor);
                return true;
            }
            else if(reversingIndex != -1){
                int flag = elevator.getFloorsQueue().get(reversingIndex);
                for(int i = reversingIndex; i < elevator.getFloorsQueue().size(); i++){
                    int val = elevator.getFloorsQueue().get(i);
                    if(val < flag && flag > staringFloor){
                        flag = elevator.getFloorsQueue().get(i);
                    }
                }
                int id = elevator.getFloorsQueue().lastIndexOf(flag);
                elevator.getFloorsQueue().add(id, staringFloor);
                return true;
            }

        }








            //not moving
            return false;
    }

    //!!!??
    public int elevatorDirection(Elevator elevator){
        int next = elevator.getFloorsQueue().get(0);//!!!??
        int current = elevator.getCurrentFloor();
        if(next - current > 0){
            //up
            return 1;
        }
        else if(next - current < 0){
            //down
            return -1;
        }
        return 0;
    }

    public int findFirstReversingIndex(List<Integer> list){
        //eg. 1, 2, 3, 2 | value 3 index 2 is reversing
        int size = list.size();
        for (int i = 1; i < size - 1; i++) {
            int current = list.get(i);
            int previous = list.get(i - 1);
            int next = list.get(i + 1);

            if ((previous < current && current > next) || (previous > current && current < next)) {
                return i;
            }
        }
        return -1;
    }








    public void listOutElevators(){
        System.out.println("Elevators(" + elevators.size() + "):");
        for (Elevator elevator : elevators) {
            System.out.println(elevator.toString());
        }
    }


}
