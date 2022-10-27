import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import axios from "axios"
import EmployeeCard from "./EmployeeCard";
import {EmployeeModel} from "./EmployeeModel";

export default function EmployeeOverview() {

    const [employees, setEmployees] = useState<EmployeeModel[]>([])
    const [newEmployee, setNewEmployee] = useState<string>("")
    const baseUrl='/api/employees/'

    useEffect(() => {
        getAllEmployees()
    },[])

    const getAllEmployees = () =>
        axios.get(baseUrl)
            .then((answer) => {
                setEmployees((answer.data))
            })
            .catch((e) => console.log("GET ERROR: " + e))

    function deleteEmployee(id: string) {
        axios.delete(baseUrl + id)
            .then(getAllEmployees)
            .catch((e) => console.log("DELETE ERROR: " + e))
    }

    const employeeList = employees.map(employee => {
        return <EmployeeCard key={employee.id} employee={employee} deleteEmployee={deleteEmployee} />
    })

    const postNewEmployee = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.post(baseUrl, {name: newEmployee})
            .catch((e) => console.log("POST ERROR: " + e))
            .then(getAllEmployees)
        setNewEmployee("")
}

    function handleStateEmployee(event: ChangeEvent<HTMLInputElement>) {
        setNewEmployee(event.target.value)
    }

    return <>
        <h1>Employees</h1>
        <ul>{employeeList}</ul>
        <form onSubmit={postNewEmployee}>
            <input type="text" value={newEmployee} onChange={handleStateEmployee} />
            <button type="submit">Add Employee</button>
        </form>
    </>
}
