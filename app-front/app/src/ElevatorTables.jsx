import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const ElevatorTables = () => {
    const elevators = [
      {
        id: 1,
        floorsLimit: [0, 6],
        floorsQueue: [1, 2],
        currentFloor: 3,
      },
      {
        id: 2,
        floorsLimit: [0, 6],
        floorsQueue: [],
        currentFloor: 0,
      },
    ];
  
    const renderTable = (elevator) => {
      const { floorsLimit, currentFloor, floorsQueue } = elevator;
      const floorBoxes = [];
  
      for (let i = 0; i <= 6; i++) {
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
        <div>
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
      <div className="d-flex justify-content-center">
        {elevators.map((elevator) => (
          <div key={elevator.id} className="mx-2">
            {renderTable(elevator)}
          </div>
        ))}
      </div>
    );
  };
  
  export default ElevatorTables;