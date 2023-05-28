package com.elevator.app.services;

import com.elevator.app.models.Elevator;
import com.elevator.app.models.UserRequest;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ElevatorManager {

    //content------------------------
    private List<Elevator> elevators;
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
            if(elevator.getFloorsQueue().size() == 0){

            }
            else if(elevator.getCurrentFloor() == elevator.getFloorsQueue().get(0)){
                elevator.getFloorsQueue().remove(0);
            }
            return true;
        }
        return false;
    }

    public boolean moveDown(Elevator elevator) {
        if (elevator.getCurrentFloor() - 1 >= elevator.getFloorsLimit().get(0)) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
            if(elevator.getFloorsQueue().size() == 0){

            }
            else if(elevator.getCurrentFloor() == elevator.getFloorsQueue().get(0)){
                elevator.getFloorsQueue().remove(0);
            }
            return true;
        }
        return false;
    }

    public void elevatorMove(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        List<Integer> floorsQueue = elevator.getFloorsQueue();

        if (!floorsQueue.isEmpty()) {
            int nextFloor = floorsQueue.get(0);

            if (nextFloor > currentFloor) {
                moveUp(elevator);
            } else if (nextFloor < currentFloor) {
                if (moveDown(elevator)) {
                    floorsQueue.remove(0);
                    return;
                }
            } else {
                floorsQueue.remove(0);
                return;
            }

            if (floorsQueue.size() == 1) {
                int lastFloor = floorsQueue.get(0);
                if (currentFloor == lastFloor - 1) {
                    return;
                }
            }

            currentFloor = elevator.getCurrentFloor();
        } else {
        }

        elevator.setCurrentFloor(currentFloor);
    }




    public void processRequestOutside(Elevator elevator, UserRequest userRequest){
        addFloors(elevator, userRequest.getStaringFloor(), userRequest.getRequestValue());
    }

    public void processRequestInside(Elevator elevator, int destinationFloor){
        int requestValue = 1;
        int passengerElevatorDirection = destinationFloor - elevator.getCurrentFloor();
        if(passengerElevatorDirection == 1){
            addFloors(elevator, destinationFloor, 1);
        }
        else if(passengerElevatorDirection == -1){
            addFloors(elevator, destinationFloor, -1);
        }
        else {
            addFloors(elevator, destinationFloor, 0);
        }
    }



    public int findOptimal(List<Elevator> elevators){
        if(elevators.size() == 0){
            return -1;
        }

        int minPath = Integer.MAX_VALUE;
        int id = -1;
        //looking for min
        for (int i = 0; i < elevators.size(); i++) {
            int path = calculatePath(elevators.get(i));
            if (path < minPath) {
                minPath = path;
                id = i;
            }
        }
        return id;
    }

    //request value -> user direction
    //request value can be -1 or 1
    public boolean addFloors(Elevator elevator, int staringFloor, int requestValue) {
        if (!(staringFloor >= elevator.getFloorsLimit().get(0) && staringFloor <= elevator.getFloorsLimit().get(1))) {
            return false;
        } else if (!(requestValue == 1 || requestValue == -1)) {
            return false;
        } else if(elevator.getFloorsQueue().size() == 0){
            elevator.getFloorsQueue().add(staringFloor);
            return true;
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
                if(id == -1){
                    elevator.getFloorsQueue().add(staringFloor);
                }
                else if(elevator.getFloorsQueue().get(id) < staringFloor){
                    elevator.getFloorsQueue().add(id + 1, staringFloor);
                }
                else{
                    elevator.getFloorsQueue().add(id, staringFloor);
                }
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
            if(elevator.getFloorsQueue().contains(staringFloor) && reversingIndex == -1 && elevator.getFloorsQueue().lastIndexOf(staringFloor) == elevator.getFloorsQueue().size() - 1){
                return true;
            }
            else if(reversingIndex == -1 ){
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
                if(id == -1){
                    elevator.getFloorsQueue().add(staringFloor);
                }
                else if(elevator.getFloorsQueue().get(id) > staringFloor){
                    elevator.getFloorsQueue().add(id + 1, staringFloor);
                }
                else{
                    elevator.getFloorsQueue().add(id, staringFloor);
                }
                return true;
            }
        }
        //if elevator goes down and user want to go down as well
        else if (elevatorDirecton == -1 && requestValue == -1) {
            System.out.println("-1 -1");
            //if elevator is under staringFloor
            //check if staringFloor is already in list
            if(elevator.getFloorsQueue().contains(staringFloor)){
                return true;
            }
            //check if staringFloor is already in list
            else if(!elevator.getFloorsQueue().contains(staringFloor)){
                int flag = elevator.getCurrentFloor();
                for (Integer i : elevator.getFloorsQueue()) {
                    if(i < flag && flag > staringFloor){
                        flag = i;
                    }
                }
                int id = elevator.getFloorsQueue().indexOf(flag);
                if(id == -1){
                    elevator.getFloorsQueue().add(staringFloor);
                }
                else if(elevator.getFloorsQueue().get(id) > staringFloor){
                    elevator.getFloorsQueue().add(id + 1, staringFloor);
                }
                else{
                    elevator.getFloorsQueue().add(id, staringFloor);
                }
                return true;
            }
        }
        //if elevator goes down and user want to go up
        else if (elevatorDirecton == -1 && requestValue == 1) {
            System.out.println("-1 1");
            int reversingIndex = findFirstReversingIndex(elevator.getFloorsQueue());
            if(elevator.getFloorsQueue().contains(staringFloor) && reversingIndex != -1 && elevator.getFloorsQueue().lastIndexOf(staringFloor) >= reversingIndex){
                return true;
            }
            else if(elevator.getFloorsQueue().contains(staringFloor) && reversingIndex == -1 && elevator.getFloorsQueue().lastIndexOf(staringFloor) == elevator.getFloorsQueue().size() - 1){
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
                    if(val > flag && flag < staringFloor){
                        flag = elevator.getFloorsQueue().get(i);
                    }
                }
                int id = elevator.getFloorsQueue().lastIndexOf(flag);
                if(id == -1){
                    elevator.getFloorsQueue().add(staringFloor);
                }
                else if(elevator.getFloorsQueue().get(id) < staringFloor){
                    elevator.getFloorsQueue().add(id + 1, staringFloor);
                }
                else{
                    elevator.getFloorsQueue().add(id, staringFloor);
                }
                return true;
            }
        }
        //not moving
        System.out.println("0 0");
        return false;
    }

    public int calculatePath(Elevator elevator) {
        int path = 0;
        int current = elevator.getCurrentFloor();
        List<Integer> list = elevator.getFloorsQueue();

        for (int i = 0; i < list.size(); i++) {
            int nextFloor = list.get(i);
            path += Math.abs(nextFloor - current);
            //System.out.println(Math.abs(nextFloor - current));
            current = nextFloor;
        }
        return path;
    }

    public int elevatorDirection(Elevator elevator){
        int next = elevator.getFloorsQueue().get(0);//!!
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