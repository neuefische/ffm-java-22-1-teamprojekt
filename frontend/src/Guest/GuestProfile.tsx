import React from "react";
import {GuestModel} from "./GuestModel/GuestModel";
import GuestCard from "./GuestCard";
import {NavLink} from "react-router-dom";

type GuestProfileProps = {
    guestList: GuestModel[];
    fetchAllGuests: () => void
}

export default function GuestProfile(props: GuestProfileProps) {

    const guestListOnBoard = props.guestList.map(guest => {
        return <GuestCard key={guest.id} guest={guest} fetchAllGuests={props.fetchAllGuests}/>
    })

    return (
        <div>
            <NavLink to="/">
                <button>Home</button>
            </NavLink>
            <h3>Guest list:</h3>
            <div>
                {guestListOnBoard}
            </div>
        </div>
    )
}
