import {EmployeeModel} from "./EmployeeModel";

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    openUpdateEmployeeModal: (id: string, name: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeCard(props: employeeProps) {

    return <>
        <li>Name: {props.employee.name} ID: {props.employee.id}
            <button onClick={() => props.deleteEmployee(props.employee.id)}>Delete Employee</button>
            <button onClick={() => props.openUpdateEmployeeModal(props.employee.id, props.employee.name)}>Edit</button>
        </li>
    </>
}
