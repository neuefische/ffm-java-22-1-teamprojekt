import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import axios from "axios"
import {EmployeeModel} from "./EmployeeModel";
import EmployeeCard from "./EmployeeCard";
import EmployeeModal from "./EmployeeModal";

export default function EmployeeOverview() {

    const [employees, setEmployees] = useState<EmployeeModel[]>([])
    const [newEmployee, setNewEmployee] = useState<string>("")
    const [isOpenModal, setIsOpenModal] = useState<boolean>(false)
    const [currentEmployeeId, setCurrentEmployeeId] = useState<string>("")

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

    const openUpdateEmployeeModalWithId = (id: string) => {
        setIsOpenModal(true)
        setCurrentEmployeeId(id);
    }

    const closeModal = () => {
        setIsOpenModal(false)
    }

    const employeeList = employees.map(employee => {
        return <EmployeeCard key={employee.id}
                             openUpdateEmployeeModal={openUpdateEmployeeModalWithId}
                             employee={employee}
                             deleteEmployee={deleteEmployee}
                             getAllEmployees={getAllEmployees}/>
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
        {isOpenModal && <EmployeeModal
            closeModal={closeModal}
            id={currentEmployeeId}
            deleteEmployee={deleteEmployee}
            getAllEmployees={getAllEmployees} />}
    </>
}
