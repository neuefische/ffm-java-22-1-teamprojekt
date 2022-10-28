import React, {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {EmployeeModel} from "./EmployeeModel";

type updateEmployeeProps = {
    closeModal: () => void
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeModal(props:updateEmployeeProps){

    const [newEmployeeName, setEmployeeName] = useState<string>(props.employee.name)

    const handleUpdateEmployeeSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/employees/"+props.employee.id, {
            "id": props.employee.id,
            "name": newEmployeeName
        })
            .catch((e) => console.log("PUT Error: " + e))
            .then(props.getAllEmployees)
        setEmployeeName("")
    }

    const handleNewEmployeeName = (event: ChangeEvent<HTMLInputElement>) => {
        setEmployeeName(event.target.value)
    }

    return (
        <div>
            <form>
                <input type="text"></input>
            <button type="button">Update</button>
            </form>
            <button onClick={props.closeModal}>Close</button>
            <form onSubmit={handleUpdateEmployeeSubmit}>
                <input value={newEmployeeName} onChange={handleNewEmployeeName}/>
                <button type={"submit"}>Update</button>
            </form>
        </div>
    );
}