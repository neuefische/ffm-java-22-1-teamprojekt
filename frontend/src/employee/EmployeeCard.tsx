import React from 'react';
import {EmployeeModel} from './EmployeeModel'

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
}


export default function EmployeeCard(props: employeeProps) {

    return <>
        <li>Name: {props.employee.name} ID: {props.employee.id}
            <button onClick={() => props.deleteEmployee(props.employee.id)}>Delete Employee</button>
        </li>
    </>
}