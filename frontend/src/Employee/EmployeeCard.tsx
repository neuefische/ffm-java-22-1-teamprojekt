import {EmployeeModel} from "./EmployeeModel";
import styled from "styled-components";

type employeeProps = {
    employee: EmployeeModel,
    deleteEmployee: (id: string) => void,
    openUpdateEmployeeModal: (id: string, name: string, regTimeStamp: string) => void,
    getAllEmployees: () => void
}

export default function EmployeeCard(props: employeeProps) {

    function changeDateToLocalDate(value: string | number | Date) {
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
        <StyledLi>
            <StyledName>Name: {props.employee.name}</StyledName>
            <StyledId>ID: {props.employee.id}</StyledId>
            <StyledRegDate>Registration
                Date: {`${changeDateToLocalDate(props.employee.regTimeStamp)} Uhr`}</StyledRegDate>
            <StyledDiv>
                <StyledButton onClick={() => props.openUpdateEmployeeModal(
                    props.employee.id, props.employee.name, props.employee.regTimeStamp)}>Edit
                </StyledButton>
                <StyledButton onClick={() => props.deleteEmployee(props.employee.id)}>Delete</StyledButton>
            </StyledDiv>
        </StyledLi>
    </>
}

const StyledLi = styled.li`
  margin: 10px;
  padding: 2px 10px 10px 5px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`

const StyledName = styled.p`
  margin-bottom: 5px;
  padding: 4px;
  font-size: 1.1rem;
`

const StyledId = styled.p`
  padding: 4px 0 0 4px;
  font-size: 0.85rem;
`

const StyledRegDate = styled.p`
  margin-bottom: 10px;
  padding: 8px;
  font-size: 0.7rem;
`

const StyledDiv = styled.div`
  display: flex;
  justify-content: right;
`

const StyledButton = styled.button`
  margin: 3px;
  padding: 5px;
  width: 75px;
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
