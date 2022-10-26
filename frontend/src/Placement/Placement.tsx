import React from 'react';
import PlacementModel from "./PlacementModel";


type PlacementProps = {
    singlePlacement: PlacementModel
}

function Placement(props: PlacementProps) {
    return (
        <li>
            <h3>Table-Nr: {props.singlePlacement.placementNr}</h3>
            <p>Total Seats: {props.singlePlacement.totalSeats}</p>
        </li>
    );
}

export default Placement;