import React, {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";

type updateEmployeeProps = {
    closeModal: () => void
    id: string,
    deleteEmployee: (id: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeModal(props:updateEmployeeProps){

    const [newEmployeeName, setEmployeeName] = useState<string>()

    const handleUpdateEmployeeSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/employees/" + props.id, {
            "id": props.id,
            "name": newEmployeeName
        })
            .catch((e) => console.log("PUT Error: " + e))
            .then(props.getAllEmployees)
            .then(props.closeModal)
    }

    const handleNewEmployeeName = (event: ChangeEvent<HTMLInputElement>) => {
        setEmployeeName(event.target.value)
    }

    return (
        <div>
            <form onSubmit={handleUpdateEmployeeSubmit}>
                <input type={"text"} value={newEmployeeName} onChange={handleNewEmployeeName}/>
                <button type={"submit"}>Update</button>
            </form>
            <button onClick={props.closeModal}>Close</button>
        </div>
    );
}
