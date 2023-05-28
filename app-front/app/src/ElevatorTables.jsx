import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import ElevatorPanel from './ElevatorPanel';
import { addElevator, addUserRequest, runRound } from './api';

const ElevatorTables = () => {
  const [elevators, setElevators] = useState([]);

  const handleAddElevator = async () => {
    const emptyData = {};
    const data = await addElevator(emptyData);
    setElevators(data);
  };

  const handleAddUserRequest = async (requestData) => {
    const data = await addUserRequest(requestData);
    setElevators(data);
  };

  const iterRound = async () => {
    const emptyData = {};
    const data = await runRound(emptyData);
    setElevators(data);
  };

  const foLoader = () => {
    iterRound();
  };

  const renderTable = (elevator) => {
    const { id, floorsLimit, currentFloor, floorsQueue } = elevator;
    const floorBoxes = [];

    for (let i = 0; i <= floorsLimit[1]; i++) {
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
        {elevators.map((elevator) => (
          <div key={elevator.id} className="mx-2">
            {renderTable(elevator)}
          </div>
        ))}
      </div>
      <ElevatorPanel addElevator={handleAddElevator} onAddUserRequest={handleAddUserRequest} />
      <br />
      <button className="btn btn-primary" onClick={foLoader}>
        Run Round
      </button>
    </div>
  );
};

export default ElevatorTables;
