import React, {useState} from 'react';
import {GuestModel} from "./GuestModel/GuestModel";
import axios from "axios";
import GuestModal from "./GuestModal/GuestModal";

type GuestCardProps = {
    guest: GuestModel;
}

export default function GuestCard(props: GuestCardProps) {
    const [editModal,setEditModal] = useState(false)

    const handleEdit = () => {
        setEditModal(!editModal)
    }

    const closeModal = () => {
        setEditModal(false)
    }

    const handlePut = () => {
        axios.put("/api/guests/" + props.guest.id, {
            firstName: props.guest.firstName,
            lastName: props.guest.lastName,
            email: props.guest.email,
        })
            .then(response =>
                response.data)
            .catch(error => console.log(error))
    }

    return (
        <>
            {editModal && <GuestModal closeModal={closeModal} guest={props.guest}/>}
            <section>
            <h3>Guest {props.guest.lastName}</h3>
        <ul>
        <li> Firstname: {props.guest.firstName}</li>
        <li> Lastname: {props.guest.lastName}</li>
        <li> email: {props.guest.email}</li>
            <button onClick={handleEdit}>close</button>
            <button onClick={handlePut}>edit</button>
        </ul>
            </section>
        </>
    );
}

