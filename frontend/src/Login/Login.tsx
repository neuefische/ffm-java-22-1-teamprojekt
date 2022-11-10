import React, {ChangeEvent, useState} from 'react';
import axios from "axios";
export default function Login() {

    const postLoginForm = () => {
        axios.post("/api/login", {
            email,
            password,
        })
            .catch((error) => {
                console.log("Error =>" + error)
            })
    }

    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");

    const handleLoginSubmit = (event: ChangeEvent<HTMLFormElement>) =>{
        event.preventDefault();
        postLoginForm();
        setEmail("");
        setPassword("");

    }

    return <>
        <form onSubmit={handleLoginSubmit}>

            <label htmlFor={"email"}>Email:</label>
            <input onChange={(e) => setEmail(e.target.value)} placeholder={"abc@hotmail.de"} type="email"/>
            <label htmlFor={"password"}>Password:</label>
            <input onChange={(e) => setPassword(e.target.value)} placeholder={"Robert12345!"} type="password"/>
            <button type={"submit"}>PUSH DE BUTTON</button>

        </form>
        </>
}