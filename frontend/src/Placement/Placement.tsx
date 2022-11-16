import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import PlacementModel from "./PlacementModel";
import axios from "axios";
import styled from "styled-components";


type PlacementProps = {
    singlePlacement: PlacementModel,
    fetchAll: () => void
}

export default function Placement(props: PlacementProps) {
    const [newValue, setNewValue] = useState<number>(0)
    const [editMode, setEditMode] = useState<boolean>(false)


    const updatePlacement = () => {
        axios
            .put("api/placements/" + props.singlePlacement.id, {totalSeats: newValue})
            .then(() => {
                props.fetchAll()
                setEditMode(false)
            })
            .catch(err => console.log(err))
    }

    const deletePlacement = () => {
        axios
            .delete("api/placements/" + props.singlePlacement.id)
            .then(() => {
                props.fetchAll()
            })
            .catch(err => console.log(err))
    }

    useEffect(() => {
        setNewValue(props.singlePlacement.totalSeats)
    }, [props.singlePlacement.totalSeats])


    const handleSubmit = (evt: FormEvent<HTMLFormElement>) => {
        evt.preventDefault()
        updatePlacement()
        // axios.put
    }

    const handleUpdateTotalSeats = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value)
            setNewValue(parseInt(evt.target.value))
    }

    const handleDelete = () => {
        console.log("delete placement Nr: ", props.singlePlacement.placementNr, " id: ", props.singlePlacement.id)
        deletePlacement();
    }

    return (
        <StyledLi>
            <StyledTable>Table-Nr: {props.singlePlacement.placementNr}</StyledTable>
            <StyledSeats>Total Seats: {props.singlePlacement.totalSeats}</StyledSeats>
            <StyledButton onClick={() => setEditMode(!editMode)}>Edit</StyledButton>
            <StyledButton onClick={handleDelete}>Delete</StyledButton>
            <StyledModal>
                {editMode && <form onSubmit={handleSubmit}>
                    <StyledInput placeholder="number from 2 to 15" min={2} max={15} type='number'
                                 onChange={handleUpdateTotalSeats} value={newValue}/>
                    <StyledButton type='submit'>Update</StyledButton>
                </form>}
            </StyledModal>
        </StyledLi>
    );
}

const StyledTable = styled.p`
  margin-bottom: 5px;
  padding: 4px;
  font-size: 1.1rem;
`

const StyledModal = styled.section`
  margin: 10px;
`

const StyledSeats = styled.p`
  padding: 4px 0 0 4px;
  font-size: 0.85rem;
`

const StyledLi = styled.li`
  margin: 10px;
  padding: 0 15px 0 15px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledButton = styled.button`
  margin: 5px;
  padding: 8px 10px;
  font-size: 0.8rem;
  cursor: pointer;
  transition-duration: 0.4s;
  background-color: var(--color-button-background);
  color: var(--color-text);
  border: none;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 5px;

  &:hover {
    background-color: var(--color-button-hover);
  }

  &:active {
    background-color: var(--color-button-active);
  }
`;
