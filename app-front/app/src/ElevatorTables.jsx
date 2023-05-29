import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import ElevatorPanel from './ElevatorPanel';
import { addElevator, addUserRequest, runRound, runReset } from './api';

const ElevatorTables = () => {
  const [elevators, setElevators] = useState([]);

  const handleAddElevator = async () => {
    try {
      const emptyData = {};
      const data = await addElevator(emptyData);
      console.log("addElevator response:", data);
      
      if (data !== undefined) {
        setElevators(data);
      } else {
        console.error("Invalid response from addElevator:", data);
      }
    } catch (error) {
      console.error("Error in handleAddElevator:", error);
    }
  };
  
  const handleAddUserRequest = async (requestData) => {
    try {
      const data = await addUserRequest(requestData);
      console.log("addUserRequest response:", data);
      
      if (data !== undefined) {
        setElevators(data);
      } else {
        console.error("Invalid response from addUserRequest:", data);
      }
    } catch (error) {
      console.error("Error in handleAddUserRequest:", error);
    }
  };
  
  const iterRound = async () => {
    try {
      const emptyData = {};
      const data = await runRound(emptyData);
  
      if (Array.isArray(data)) {
        setElevators(data);
      } else {
        console.error("Invalid response from runRound:", data);
      }
    } catch (error) {
      console.error("Error in iterRound:", error);
    }
  };
  
  
  const foRound = async () => {
    try {
      await iterRound();
    } catch (error) {
      console.error("Error in foRound:", error);
    }
  };

  const foReset = () => {
    runReset()
    setElevators([]);
  };
  
  const renderTable = (elevator) => {
    const { id, floorsLimit, currentFloor, floorsQueue } = elevator;
    const floorBoxes = [];
  
    for (let i = floorsLimit[1]; i >= 0; i--) { // Reverse the loop order
      floorBoxes.push(
        <tr key={i}>
          <td
            style={{
              background: currentFloor === i ? 'green' : 'transparent',
            }}
          >
            {i}
          </td>
        </tr>
      );
    }

    const queueText = floorsQueue.length > 0 ? floorsQueue.join(', ') : '-';

    return (
      <div key={id}>
        <h3>Elevator {id}</h3>
        <table className="table table-bordered">
          <tbody>{floorBoxes}</tbody>
        </table>
        <table className="table table-bordered">
          <tbody>
            <tr>
              <th>Floor queue:</th>
            </tr>
            <tr>
              <td>{queueText}</td>
            </tr>
          </tbody>
        </table>
      </div>
    );
  };


  return (
    <div>
      <div className="d-flex justify-content-center">
        {elevators
          .map((elevator) => (
            <div key={elevator.id} className="mx-2">
              {renderTable(elevator)}
            </div>
          ))}
      </div>
      <ElevatorPanel addElevator={handleAddElevator} onAddUserRequest={handleAddUserRequest} />
      <br />
      <button className="btn btn-primary" onClick={foRound}>
        Run Round
      </button>
      <br></br>
      <br></br>
      <button className="btn btn-danger" onClick={foReset}>
        Reset
      </button>
    </div>
  );
};

export default ElevatorTables;
