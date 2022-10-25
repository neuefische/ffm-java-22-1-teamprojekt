import React, {useEffect, useState} from 'react';
import axios from "axios"
import EmployeeCard from "./EmployeeCard";
import {EmployeeModel} from "./EmployeeModel";

export default function EmployeeOverview() {

    const [employees, setEmployees] = useState<EmployeeModel[]>([])

    useEffect(() => {
        getAllEmployees()
    },[])

    const getAllEmployees = () =>
        axios.get('/api/employees')
            .then((answer) => {
                setEmployees((answer.data))
            })
            .catch((error) => console.log(error))

    const employeeList = employees.map(employee => {
        return <EmployeeCard employee={employee} />
    })

    return <>
        <h1>Employees</h1>
        {employeeList}
    </>
}