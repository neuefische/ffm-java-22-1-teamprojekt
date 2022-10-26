import React, {useEffect, useState} from 'react';
import axios from "axios";
import PlacementModel from "./PlacementModel";
import Placement from "./Placement";
import AddPlacementForm from "./AddPlacementForm";


function PlacementPage() {
    const [placementsData, setPlacementsData] = useState<PlacementModel[]>()

    const fetchPlacementData = () => {
        axios.get("/api/placements")
            .then((response) => {
                return response.data
            })
            .catch((error) => {
                console.log('[Axios Error] => :', error)
            })
            .then(data => setPlacementsData(data))
    }

    useEffect(() => {
        fetchPlacementData();
    }, [])


    return (
        <div>
            <h1>Tables:</h1>
            <AddPlacementForm fetchAll={fetchPlacementData}/>
            <ul>
                {placementsData
                    ?.sort((a, b) => a.placementNr > b.placementNr ? 1 : -1)
                    .map((singlePlacement =>
                        <Placement key={singlePlacement.id} singlePlacement={singlePlacement}/>))}
            </ul>
        </div>
    );
}

export default PlacementPage;