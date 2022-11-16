import {GuestModel} from "../GuestModel/GuestModel";
import {ChangeEvent, useState} from "react";
import axios from "axios";
import styled from "styled-components";

type ModalProps = {
    closeModal: () => void
    guest: GuestModel
    fetchAllTasks: () => void
}

export default function GuestModal(props: ModalProps) {
    const [firstName, setFirstName] = useState(props.guest.firstName)
    const [lastName, setLastName] = useState(props.guest.lastName)
    const [email, setEmail] = useState(props.guest.email)

    function handleNewFirstName(event: ChangeEvent<HTMLInputElement>) {
        setFirstName(event.target.value)
    }

    function handleNewLastName(event: ChangeEvent<HTMLInputElement>) {
        setLastName(event.target.value)
    }

    function handleNewEmail(event: ChangeEvent<HTMLInputElement>) {
        setEmail(event.target.value)
    }

    function updateGuest() {
        axios.put("/api/guests/" + props.guest.id, {
            id: props.guest.id,
            firstName,
            lastName,
            email
        })
            .then(response => {
                props.fetchAllTasks()
                props.closeModal()
                return response.data
            })
            .catch(error => console.log(error))
    }

    return (
        <StyledDiv>
            <StyledLabel>FirstName</StyledLabel>
            <StyledInput type="text" value={firstName} onChange={handleNewFirstName}/>
            <StyledLabel>LastName</StyledLabel>
            <StyledInput type="text" value={lastName} onChange={handleNewLastName}/>
            <StyledLabel>FirstName</StyledLabel>
            <StyledInput type="text" value={email} onChange={handleNewEmail}/>
            <StyledButton onClick={updateGuest}>Update</StyledButton>
            <StyledButton onClick={props.closeModal}>Cancel</StyledButton>
        </StyledDiv>
    )
}

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 5px;
`

const StyledLabel = styled.label`
  
  font-size: 0.8rem;
`

const StyledInput = styled.input`
  margin: 5px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledButton = styled.button`
  padding: 6px 8px;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 3px;
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
