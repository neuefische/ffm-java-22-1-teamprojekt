import {EmployeeModel} from "./EmployeeModel";

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    openUpdateEmployeeModal: (id: string, name: string, regTimeStamp: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeCard(props: employeeProps) {

    function getCurrentDate(value: string | number | Date) {
        return new Date(value)
            .toLocaleDateString('de-DE', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
            })
            .replace(/[.,]/g, match => (match === '.' ? '.' : ''));
    }

    return <>
        <li>
            <p>Name: {props.employee.name}</p>
            <p>ID: {props.employee.id}</p>
            <p>{`${getCurrentDate(props.employee.regTimeStamp)} Uhr`}
            </p>
            <button onClick={() => props.deleteEmployee(props.employee.id)}>Delete Employee</button>
            <button onClick={() => props.openUpdateEmployeeModal(
                props.employee.id,
                props.employee.name,
                props.employee.regTimeStamp)}>Edit</button>
        </li>
    </>
}
