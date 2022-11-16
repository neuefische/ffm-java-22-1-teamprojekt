import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import axios from "axios"
import {EmployeeModel} from "./EmployeeModel";
import EmployeeCard from "./EmployeeCard";
import EmployeeModal from "./EmployeeModal";
import styled from "styled-components";
import { Icon } from '@iconify/react';

export default function EmployeeOverview() {

    const [employees, setEmployees] = useState<EmployeeModel[]>([])
    const [newEmployee, setNewEmployee] = useState<string>("")
    const [isOpenModal, setIsOpenModal] = useState<boolean>(false)
    const [currentEmployeeId, setCurrentEmployeeId] = useState<string>("")

    const baseUrl = '/api/employees/'

    useEffect(() => {
        getAllEmployees()
    }, [])

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
        <StyledSection>
            <h2>Employees:</h2>
            <StyledUl>{employeeList}</StyledUl>
            <StyledForm onSubmit={postNewEmployee}>
                <StyledInput type="text" value={newEmployee} onChange={handleStateEmployee}/>
                <StyledButton type="submit"><Icon icon="material-symbols:add" height="14" /> Add Employee</StyledButton>
            </StyledForm>
            <StyledModal>
                {isOpenModal && <EmployeeModal
                    closeModal={closeModal}
                    id={currentEmployeeId}
                    deleteEmployee={deleteEmployee}
                    getAllEmployees={getAllEmployees}/>}
            </StyledModal>
        </StyledSection>
    </>
}

const StyledModal = styled.section`
  position: fixed;
  top: 40vh;
  left: 40vw;
`

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`

const StyledUl = styled.ul`
  padding: 0;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`

const StyledForm = styled.form`
  display: flex;
  justify-content: right;
`

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledButton = styled.button`
  padding: 8px 10px;
  font-size: 0.8rem;
  cursor: pointer;
  margin: 3px;
  transition-duration: 0.4s;
  background-color: var(--color-button-background);
  color: var(--color-text);
  border: none;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 5px;

  &:hover {
    background-color: var(--color-button-hover);
  }

  &:active {
    background-color: var(--color-button-active);
  }
`;
