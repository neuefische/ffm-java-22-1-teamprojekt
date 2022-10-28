import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import {EmployeeModel} from "./EmployeeModel";
import axios from "axios";

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    visibleModal: (id: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeCard(props: employeeProps) {

    return <>
        <li>Name: {props.employee.name} ID: {props.employee.id}
            <button onClick={() => props.deleteEmployee(props.employee.id)}>Delete Employee</button>
            <button onClick={() => props.visibleModal(props.employee.id)}>Edit</button>
        </li>
    </>
}
