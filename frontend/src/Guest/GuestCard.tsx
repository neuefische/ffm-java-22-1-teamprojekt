import React from 'react';
import {GuestModel} from "./GuestModel";

type GuestProps = {
    guest: GuestModel;
}

export default function GuestCard(props: GuestProps) {
    return (
        <>
            <h3>Gast {props.guest.lastName}</h3>
        <ul>
        <li> Firstname: {props.guest.firstName}</li>
        <li> Lastname: {props.guest.lastName}</li>
        <li> email: {props.guest.email}</li>
        </ul>

        </>
    );
}

