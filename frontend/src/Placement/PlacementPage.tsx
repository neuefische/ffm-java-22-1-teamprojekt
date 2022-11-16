import React, {useEffect, useState} from 'react';
import axios from "axios";
import PlacementModel from "./PlacementModel";
import Placement from "./Placement";
import AddPlacementForm from "./AddPlacementForm";
import styled from "styled-components";


export default function PlacementPage() {
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
        <StyledSection>
            <h2>Tables:</h2>

            <StyledUl>
                {placementsData
                    ?.sort((a, b) => a.placementNr > b.placementNr ? 1 : -1)
                    .map((singlePlacement =>
                        <Placement key={singlePlacement.id} fetchAll={fetchPlacementData}
                                   singlePlacement={singlePlacement}/>))}
            </StyledUl>
            <StyledAdd>
                <AddPlacementForm fetchAll={fetchPlacementData}/>
            </StyledAdd>
        </StyledSection>
    );
}

const StyledAdd = styled.div`
  display: flex;
  justify-content: right;
`

const StyledUl = styled.ul`
  padding: 0;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`