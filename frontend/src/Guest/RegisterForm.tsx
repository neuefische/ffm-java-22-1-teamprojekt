import React, {ChangeEvent, useEffect, useState} from 'react';
import axios from "axios";

export default function RegisterForm() {

    const postForm = () => {
        axios.post("/api/guests", {
            // JSON.stringify({firstName,lastName, birthday, email, password, confirmPassword}),
            firstName:firstName,
            lastName:lastName,
            birthday:birthday,
            email:email,
            password:password,
            confirmPassword:confirmPassword,
        })
            .then((response) => response.data)
            .catch((error) => {
                console.log("Error =>" + error)
            })
    }

    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setLastName] = useState<string>("");
    const [birthday, setBirthday] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [confirmPassword, setConfirmPassword] = useState<string>("");

    const handleFormSubmit = (event: ChangeEvent<HTMLFormElement>) => {
        event.preventDefault();
        postForm();
        alert("Successful registration. Please proceed with login.")
    }

    return (
        <form onSubmit={handleFormSubmit}>
            <label>First name:</label>
            <input type='text'
                   value={firstName}
                   onChange={(e) => setFirstName(e.target.value)}
                   placeholder={"John"}/>
            <label>Last name:</label>
            <input type='text'
                   value={lastName}
                   onChange={(e) => setLastName(e.target.value)}
                   placeholder="Doe"/>
            <label>Birthday:</label>
            <input type='text'
                   value={birthday}
                   onChange={(e) => setBirthday(e.target.value)}
                   placeholder="01.01.1999"/>
            <label>E-Mail:</label>
            <input type='text'
                   value={email}
                   onChange={(e) => setEmail(e.target.value)}
                   placeholder="abc@gmail.com"/>
            <label>Password:</label>
            <input type='text'
                   value={password}
                   onChange={(e) => setPassword(e.target.value)}
                   placeholder="Bello123"/>
            <label>ConfirmPassword:</label>
            <input type='text'
                   value={confirmPassword}
                   onChange={(e) => setConfirmPassword(e.target.value)}
                   placeholder="Bello123"/>
            <button>Register</button>
        </form>
    );
}

