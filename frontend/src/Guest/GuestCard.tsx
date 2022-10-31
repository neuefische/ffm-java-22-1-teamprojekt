import React, {useState} from 'react';
import {GuestModel} from "./GuestModel/GuestModel";
import GuestModal from "./GuestModal/GuestModal";
import axios from "axios";

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
                console.log('[Error von GET]: =>' + error)
                if (error.status === 404) setMessageStatus('Error: Delete not successful!!')
            })
            .then((status) => {
                if (status === 200) {
                    setMessageStatus('Guest ' + props.guest.firstName+" "+props.guest.lastName + ' successfully deleted.');
                }
            }).then(() => setTimeout(() => props.fetchAllGuests(), 2000))
    }

    // GuestCard

    return (
        <>
            {editModal &&
                <GuestModal closeModal={closeModal} guest={props.guest} fetchAllTasks={props.fetchAllGuests}/>}
            {messageStatus && <p>{messageStatus}</p>}
            <section>
                <h3>Guest {props.guest.lastName}</h3>
                <div>
                    <p> Firstname: {props.guest.firstName}</p>
                    <p> Lastname: {props.guest.lastName}</p>
                    <p> email: {props.guest.email}</p>
                    <button onClick={handleEdit}>Edit Guest</button>
                    <button onClick={deleteGuest}>delete</button>

                </div>
            </section>
        </>
    );
}

