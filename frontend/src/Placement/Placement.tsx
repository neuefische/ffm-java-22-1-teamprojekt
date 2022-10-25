import React from 'react';
import PlacementModel from "./PlacementModel";


type PlacementProps = {
    singlePlacement: PlacementModel
}

function Placement(props: PlacementProps) {
    return (
        <li>
            <h2>Tisch Nr: {props.singlePlacement.placementNr}</h2>
        </li>
    );
}

export default Placement;