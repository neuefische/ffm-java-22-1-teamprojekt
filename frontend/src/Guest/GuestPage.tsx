import React, {useEffect, useState} from "react";
import axios from "axios";
import {GuestModel} from "./GuestModel/GuestModel"
import GuestProfile from "./GuestProfile";


export default function GuestPage() {
    const [guestList,setGuestList] = useState<GuestModel[]>([])

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
    return (
       <>
        <GuestProfile  guestList={guestList} fetchAllGuests={fetchAllGuests}/>
       </>
    );
}
