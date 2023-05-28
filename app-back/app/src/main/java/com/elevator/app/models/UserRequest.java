package com.elevator.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private int requestValue;
    private int staringFloor;
}