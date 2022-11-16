import React, {ChangeEvent, FormEvent, useState} from 'react';
import axios from 'axios';
import PlacementModel from "./PlacementModel";
import styled from "styled-components";
import {Icon} from "@iconify/react";

type AddPlacementFormProps = {
    fetchAll: () => void;
}

export default function AddPlacementForm(props: AddPlacementFormProps) {
    const [tableNumber, setTableNumber] = useState<string>('')
    const [totalSeats, setTotalSeats] = useState<string>('')

    const postNewPlacement = (newPlacement: Omit<PlacementModel, 'id'>) => {
        axios.post("api/placements", newPlacement)
            .then(() => {
                props.fetchAll();
                setTableNumber("")
                setTotalSeats("")
            })
            .catch((error) => {
                console.log(error)
            })

    }

    const handleSubmit = (evt: FormEvent<HTMLFormElement>) => {
        evt.preventDefault();
        console.log('[Submit Form] => ', 'Table number: ' + tableNumber, ' Total seats: ' + totalSeats)
        postNewPlacement({placementNr: parseInt(tableNumber), totalSeats: parseInt(totalSeats)})

    }

    const handleTableNumberChange = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value)
            setTableNumber(evt.target.value)
    }
    const handleTotalSeatsChange = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value) setTotalSeats(evt.target.value)
    }

    return (
        <StyledForm onSubmit={handleSubmit}>
            <StyledDiv>
                <StyledLabel htmlFor='placement-Nr'>Table Nr.: </StyledLabel>
                <StyledInput placeholder="Table number" type='number' id='placement-Nr'
                             onChange={handleTableNumberChange} value={tableNumber}/>
                <StyledLabel htmlFor='total-seats'>Total Seats: </StyledLabel>
                <StyledInput placeholder="Number from 2 to 15" min={2} max={15} type='number' id='total-seats'
                             onChange={handleTotalSeatsChange} value={totalSeats}/>
            </StyledDiv>
            <StyledButton><Icon icon="material-symbols:add" height="14"/> Add Placement</StyledButton>
        </StyledForm>
    );
}

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledLabel = styled.label`
  font-size: 0.8rem;
`

const StyledForm = styled.form`
  display: flex;
  align-items: center;
  margin: 20px;
`;

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 20px 10px 0 0;
  padding: 10px;
`;

const StyledButton = styled.button`
  position: relative;
  top: 15px;
  padding: 8px 10px;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 3px;
  width: 125px;
  height: 40px;
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

