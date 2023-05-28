import React, { useState } from 'react';

const ElevatorPanel = ({ addElevator, onAddUserRequest }) => {
  const [direction, setDirection] = useState(1);
  const [startFloor, setStartFloor] = useState(0);

  const handleAddUserRequest = () => {
    const requestData = {
        "requestValue": direction,
        "staringFloor": startFloor,
    };

    onAddUserRequest(requestData);
  };

  return (
    <div>
      <button className="btn btn-primary" onClick={addElevator}>
        Add Elevator
      </button>
      <br />
      <div className="form-group">
        <label htmlFor="directionSelect">Passenger Direction:</label>
        <select
          className="form-control"
          id="directionSelect"
          value={direction}
          onChange={(e) => setDirection(Number(e.target.value))}
        >
          <option value={1}>Up</option>
          <option value={-1}>Down</option>
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="startFloorSelect">Start Floor:</label>
        <select
          className="form-control"
          id="startFloorSelect"
          value={startFloor}
          onChange={(e) => setStartFloor(Number(e.target.value))}
        >
          {[0, 1, 2, 3, 4, 5, 6].map((floor) => (
            <option key={floor} value={floor}>
              {floor}
            </option>
          ))}
        </select>
      </div>
      <br />
      <button className="btn btn-primary" onClick={handleAddUserRequest}>
        Add user request
      </button>
    </div>
  );
};

export default ElevatorPanel;
