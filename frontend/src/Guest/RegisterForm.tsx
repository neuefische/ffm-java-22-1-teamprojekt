import React, {ChangeEvent, useState} from 'react';
import axios from "axios";

export default function RegisterForm() {

    const postForm = () => {
        axios.post("/api/guests", {
            firstName,
            lastName,
            email,
            password,
            confirmPassword,
        })
            .catch((error) => {
                console.log("Error =>" + error)
            })
    }

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const handleFormSubmit = (event: ChangeEvent<HTMLFormElement>) => {
        event.preventDefault();
        postForm();
        setFirstName("")
        setLastName("")
        setEmail("")
        setPassword("")
        setConfirmPassword("")

    }

    return (
        <form onSubmit={handleFormSubmit}>
            <label htmlFor="firstName">First name:</label>
            <input type='text'
                   id="firstName"
                   value={firstName}
                   onChange={(e) => setFirstName(e.target.value)}
                   placeholder={"John"}/>

            <label htmlFor={"lastName"}>Last name:</label>
            <input type='text'
                   id="lastName"
                   value={lastName}
                   onChange={(e) => setLastName(e.target.value)}
                   placeholder="Doe"/>

            <label htmlFor={"email"}>E-Mail:</label>
            <input type='text'
                   id="email"
                   value={email}
                   onChange={(e) => setEmail(e.target.value)}
                   placeholder="abc@gmail.com"/>

            <label htmlFor={"password"}>Password:</label>
            <input type='text'
                   id="password"
                   value={password}
                   onChange={(e) => setPassword(e.target.value)}
                   placeholder="Bello123"/>

            <label htmlFor={"confirmPassword"}>ConfirmPassword:</label>
            <input type='text'
                   id="confirmPassword"
                   value={confirmPassword}
                   onChange={(e) => setConfirmPassword(e.target.value)}
                   placeholder="Bello123"/>
            <button>Register</button>
        </form>
    );
}

