import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import {EmployeeModel} from "./EmployeeModel";
import axios from "axios";

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    visibleModal: () => void,
    getAllEmployees: () => void
}

export default function EmployeeCard(props: employeeProps) {

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

    return <>
        <li>Name: {props.employee.name} ID: {props.employee.id}
            <button onClick={() => props.deleteEmployee(props.employee.id)}>Delete Employee</button>
            <button onClick={() => props.visibleModal()}>Edit</button>
        </li>
        <form onSubmit={handleUpdateEmployeeSubmit}>
            <input value={newEmployeeName} onChange={handleNewEmployeeName}/>
            <button type={"submit"}>Update</button>
        </form>
    </>
}
