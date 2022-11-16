import React, {useState} from 'react';
import axios from "axios";
import PasswordChecklist from "react-password-checklist"
import {useNavigate} from "react-router-dom";
import styled from "styled-components";
import {Icon} from '@iconify/react';

export default function RegisterForm() {

    const postForm = () => {
        axios.post("/api/guests", {
            firstName,
            lastName,
            email,
            password,
            confirmPassword,
        })
            .then((response) => response.status)
            .catch((error) => {
                console.log("Error =>" + error)
            })
            .then((status) => {
                if (status === 200) {
                    setMessageStatus(firstName + " " + lastName + ' successfully created.');
                }
            })
            .then(() => setTimeout(() => setBackHome(), 2000))
    }

    const [messageStatus, setMessageStatus] = useState('')
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const setBackHome = () => {
        navigate("/")
    }

    const isValidEmail = (email: string) => {
        return /.@./.test(email);
    }

    const handleFormSubmit = (event: any) => {
        event.preventDefault();
        if (!isValidEmail(email)) {
            setError("Email is invalid");
            return;
        } else {
            setError("");
        }
        postForm();
        setFirstName("");
        setLastName("");
        setEmail("");
        setPassword("");
        setConfirmPassword("");
    }

    return <>
        <StyledSection>
            <h2>Registration:</h2>
            <StyledForm onSubmit={handleFormSubmit}>
                <StyledDiv1>
                    <StyledLabel htmlFor="firstName">First name:</StyledLabel>
                    <StyledInput type='text'
                                 id="firstName"
                                 value={firstName}
                                 onChange={(e) => setFirstName(e.target.value)}
                                 placeholder={"John"}/>

                    <StyledLabel htmlFor={"lastName"}>Last name:</StyledLabel>
                    <StyledInput type='text'
                                 id="lastName"
                                 value={lastName}
                                 onChange={(e) => setLastName(e.target.value)}
                                 placeholder="Doe"/>

                    <StyledLabel htmlFor={"email"}>E-Mail:</StyledLabel>
                    <StyledInput type='text'
                                 id="email"
                                 value={email}
                                 onChange={(e) => setEmail(e.target.value)}
                                 placeholder="abc@gmail.com"/>

                    <StyledLabel htmlFor={"password"}>Password:</StyledLabel>
                    <StyledInput type='text'
                                 id="password"
                                 value={password}
                                 onChange={(e) => setPassword(e.target.value)}
                                 placeholder="Bello123!"/>

                    <StyledLabel htmlFor={"confirmPassword"}>ConfirmPassword:</StyledLabel>
                    <StyledInput type='text'
                                 id="confirmPassword"
                                 value={confirmPassword}
                                 onChange={(e) => setConfirmPassword(e.target.value)}
                                 placeholder="Bello123!"/>

                    {error && <StyledMessage>{error}</StyledMessage>}
                    {messageStatus && <StyledMessage>{messageStatus}</StyledMessage>}
                </StyledDiv1>
                <StyledDiv2>
                    <PasswordChecklist
                        rules={["minLength", "specialChar", "number", "capital", "match"]}
                        minLength={8}
                        value={password}
                        valueAgain={confirmPassword}
                        messages={{
                            minLength: "Password must have at least 8 characters",
                        }}
                    />
                </StyledDiv2>

            </StyledForm>
            <StyledDiv2>
                <StyledButton onClick={handleFormSubmit}>
                    <Icon icon="mdi:register" width="14"/> Register now</StyledButton>
            </StyledDiv2>
        </StyledSection>
    </>;
}

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`

const StyledLabel = styled.label`
  font-size: 0.8rem;
`

const StyledButton = styled.button`
  margin: 3px;
  padding: 10px;
  width: 125px;
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

const StyledMessage = styled.p`
  margin: 10px;
  padding: 8px;
  font-size: 0.8rem;
`

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledDiv1 = styled.div`
  display: flex;
  flex-direction: column;
  margin: 20px;
  padding: 10px;
`;

const StyledDiv2 = styled.div`
  display: flex;
  justify-content: center;
  padding: 20px;
`;

const StyledForm = styled.form`
  display: flex;
  align-self: center;
  align-items: center;
`;
