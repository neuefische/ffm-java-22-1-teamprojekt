import React from 'react';
import {EmployeeModel} from './EmployeeModel'

type employeeProps = {
    employee: EmployeeModel,
}

export default function EmployeeCard(props: employeeProps) {
    return <>
        <li>Name: {props.employee.name} ID: {props.employee.id}</li>
    </>
}