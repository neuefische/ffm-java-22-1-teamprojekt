import React, {useEffect, useState} from "react";
import axios from "axios";
import {GuestModel} from "./GuestModel/GuestModel"
import styled from "styled-components";
import GuestCard from "./GuestCard";

export default function GuestPage() {
    const [guestList, setGuestList] = useState<GuestModel[]>([])

    useEffect(() => {
        fetchAllGuests()
    }, [])

    const fetchAllGuests = () => {
        axios.get("/api/guests")
            .then((response) => response.data)
            .catch((error) => {
                console.log('[Error von GET]: =>' + error)
            })
            .then((data) => {
                setGuestList(data)
            })
    }

    const guestListOnBoard = guestList.map(guest => {
        return <GuestCard key={guest.id} guest={guest} fetchAllGuests={fetchAllGuests}/>
    })


    return <>
        <StyledSection>
            <h2>Guests List:</h2>
            <StyledUl>{guestListOnBoard}</StyledUl>
        </StyledSection>
    </>;
}

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`

const StyledUl = styled.ul`
  padding: 0;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`
