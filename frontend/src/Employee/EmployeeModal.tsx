import React, {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import styled from "styled-components";

type updateEmployeeProps = {
    closeModal: () => void
    id: string,
    deleteEmployee: (id: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeModal(props: updateEmployeeProps) {

    const [newEmployeeName, setEmployeeName] = useState<string>()

    const handleUpdateEmployeeSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/employees/" + props.id, {
            "id": props.id,
            "name": newEmployeeName
        })
            .catch((e) => console.log("PUT Error: " + e))
            .then(props.getAllEmployees)
            .then(props.closeModal)
    }

    const handleNewEmployeeName = (event: ChangeEvent<HTMLInputElement>) => {
        setEmployeeName(event.target.value)
    }

    return (
        <StyledArticle>
            <StyledName>Please insert new name:</StyledName>
            <StyledForm onSubmit={handleUpdateEmployeeSubmit}>
                <StyledInput type={"text"} value={newEmployeeName} onChange={handleNewEmployeeName}/>
                <StyledButton type={"submit"}>Update</StyledButton>
            </StyledForm>
            <StyledDiv>
                <StyledButton onClick={props.closeModal}>Close</StyledButton>
            </StyledDiv>
        </StyledArticle>
    );
}

const StyledArticle = styled.article`
  position: absolute;
  flex-direction: column;
  padding: 50px;
  background-color: var(--color-lightgrey);
  border: 1px solid var(--color-black);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledForm = styled.form`
  display: flex;
  justify-content: center;
`;

const StyledDiv = styled.div`
  position: relative;
  top: 40px;
  left: 195px;
`

const StyledName = styled.p`
  position: relative;
  top: -35px;
  left: -25px;
  margin-bottom: 5px;
  padding: 4px;
  font-size: 1.1rem;
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
