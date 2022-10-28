import React, {useState, useEffect} from "react";
import {GuestModel} from "./GuestModel";
import axios from "axios";
import GuestCard from "./GuestCard";
import {NavLink} from "react-router-dom";

export default function GuestProfile() {

    const [guestList,setGuestList] = useState<GuestModel[]>([]);
    const [editModal, setEditModal] = useState(false)

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

    const handleEdit = () => {
        setEditModal(!editModal)
    }

    const guestListOnBoard = guestList.map(guest => {
        return <>
            <GuestCard key={guest.id} guest={guest}/>
            <button onClick={handleEdit}>edit</button>
        </>
    })

    const handlePut = () => {
        axios.put("/api/guests/", {
                firstName,
                lastName,
                email
        })
        .then(response => fetchAllGuests() {
        })
        .catch(error => console.log(error))
    }

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