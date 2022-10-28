import {GuestModel} from "./GuestModel";
import {ChangeEvent, useState} from "react";
import axios from "axios";

type ModalProps = {
    closeModal : () => void
    guest: GuestModel
}

export default function GuestModal(props: ModalProps){
    const [firstName,setFirstName] = useState(props.guest.firstName)
    const [lastName,setLastName] = useState(props.guest.lastName)
    const [email,setEmail]=useState(props.guest.email)

    function handleNewFirstName(event:ChangeEvent<HTMLInputElement>){
        setFirstName(event.target.value)
    }
    function handleNewLastName(event:ChangeEvent<HTMLInputElement>){
        setLastName(event.target.value)
    }
    function handleNewEmail(event:ChangeEvent<HTMLInputElement>){
        setEmail(event.target.value)
    }

    function updateTask() {
        axios.put("/api/guest/" + props.guest.id, {
            id:props.guest.id,
            firstName,
            lastName,
            email
        })
            .then(response => {
                props.closeModal()
                return response.data
            })
            .catch(error => console.log(error))
    }
    return (
        <div>
            <label>FirstName</label>
            <input type="text" />
        </div>
    )
}