import React, {useState} from 'react';
import {GuestModel} from "./GuestModel/GuestModel";
import GuestModal from "./GuestModal/GuestModal";
import axios from "axios";
import styled from "styled-components";

type GuestCardProps = {
    guest: GuestModel;
    fetchAllGuests: () => void
}

export default function GuestCard(props: GuestCardProps) {
    const [editModal, setEditModal] = useState(false)
    const [messageStatus, setMessageStatus] = useState('')

    const handleEdit = () => {
        setEditModal(!editModal)
    }

    const closeModal = () => {
        setEditModal(false)
    }

    const deleteGuest = () => {
        axios.delete("/api/guests/" + props.guest.id)
            .then((response) => response.status)
            .catch((error) => {
                if (error.status === 404) setMessageStatus('Error: Delete not successful!!')
            })
            .then((status) => {
                if (status === 200) {
                    setMessageStatus(props.guest.firstName + " " + props.guest.lastName + ' successfully deleted.');
                }
            })
            .then(() => setTimeout(() => props.fetchAllGuests(), 2000))
    }

    return (
        <>

            <StyledLi>
                <StyledName>
                    {props.guest.firstName}&nbsp;{props.guest.lastName}
                </StyledName>

                <StyledMail>
                    E-Mail: {props.guest.email}
                </StyledMail>
                <StyledDiv>
                <StyledButton onClick={handleEdit}>Edit Guest</StyledButton>
                <StyledButton onClick={deleteGuest}>delete</StyledButton>
                    </StyledDiv>
                {editModal &&
                    <GuestModal closeModal={closeModal}
                                guest={props.guest}
                                fetchAllTasks={props.fetchAllGuests}/>}
                {messageStatus && <StyledDeleteMessage>{messageStatus}</StyledDeleteMessage>}
            </StyledLi>
        </>
    );
}

const StyledLi = styled.li`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10px;
  padding: 2px 5px 10px 5px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`

const StyledDiv = styled.div`
  display: flex;
  justify-content: center;
  padding: 10px;
`

const StyledName = styled.p`
  margin-bottom: 5px;
  padding: 4px;
  font-size: 1.1rem;
`

const StyledMail = styled.p`
  padding: 4px 0 0 4px;
  font-size: 0.85rem;
`

const StyledDeleteMessage = styled.p`
  margin-bottom: 10px;
  padding: 8px;
  font-size: 0.85rem;
`

const StyledButton = styled.button`
  margin: 3px;
  padding: 5px;
  width: 75px;
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
