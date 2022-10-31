import React, {useState} from 'react';
import {GuestModel} from "./GuestModel/GuestModel";
import GuestModal from "./GuestModal/GuestModal";

type GuestCardProps = {
    guest: GuestModel;
    fetchAllGuests: () => void
}

export default function GuestCard(props: GuestCardProps) {
    const [editModal,setEditModal] = useState(false)

    const handleEdit = () => {
        setEditModal(!editModal)
    }

    const closeModal = () => {
        setEditModal(false)
    }

    return (
        <>
            {editModal && <GuestModal closeModal={closeModal} guest={props.guest} fetchAllTasks={props.fetchAllGuests}/>}
            <section>
            <h3>Guest {props.guest.lastName}</h3>
        <div>
        <p> Firstname: {props.guest.firstName}</p>
        <p> Lastname: {props.guest.lastName}</p>
        <p> email: {props.guest.email}</p>
            <button onClick={handleEdit}>open modal</button>

        </div>
            </section>
        </>
    );
}

