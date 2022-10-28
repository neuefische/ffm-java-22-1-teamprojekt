import React, {useState, useEffect} from "react";
import {GuestModel} from "./GuestModel/GuestModel";
import axios from "axios";
import GuestCard from "./GuestCard";
import {NavLink} from "react-router-dom";

type GuestProfileProps = {
    guestList: GuestModel[];
    fetchAllGuests: () => void
}

export default function GuestProfile(props: GuestProfileProps) {

    const [guestList,setGuestList] = useState<GuestModel[]>([]);

        const fetchAllGuests = () => {
        axios.get("/api/guests")
            .then(response => {
                props.fetchAllGuests()
                return response.data
            })
            .catch((error) => {
                console.log('[Error von GET]: =>' + error)
            })
            .then((data) => {
                setGuestList(data)
            })
    }

    useEffect(() => {
        fetchAllGuests()
    }, [])

    const guestListOnBoard = guestList.map(guest => {
        return <>
            <GuestCard key={guest.id} guest={guest}/>
        </>
    })

    return (
        <div>
            <NavLink to="/">
                <button>Home</button>
            </NavLink>
            <h3>Guest list:</h3>
        <ol>
            {guestListOnBoard}
        </ol>
        </div>
    )
}