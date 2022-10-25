import React, {useEffect, useState} from 'react';
import axios from "axios";
import PlacementModel from "./PlacementModel";
import Placement from "./Placement";


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
            <ul>
                {placementsData?.map((singlePlacement => <Placement key={singlePlacement.id}
                                                                    singlePlacement={singlePlacement}/>))}
            </ul>
        </div>
    );
}

export default PlacementPage;