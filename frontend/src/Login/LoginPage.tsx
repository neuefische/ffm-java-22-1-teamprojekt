import React, {ChangeEvent, useEffect, useState} from 'react';
import axios from "axios";

export default function LoginPage() {

    const [password, setPassword] = useState<string>("");
    const [username, setUsername] = useState<string>("");
    const [loggedInUsername, setLoggedInUsername] = useState<string>("")

    const postLoginForm = () => {
        axios.get("/api/users/login", {
            auth: {
               "username": username,
               "password": password
            }
        })
            .catch(error => console.error("Error =>" + error))
            .then(() => getLoggedInUsername())
    }

    const getLoggedInUsername = () => {
        axios.get("/api/users/me")
            .then(response => response.data)
            .then(setLoggedInUsername)
            .catch(error => console.error("Error =>" + error.message))
    }

    useEffect(getLoggedInUsername, []);



    const handleLogout = () => {
        axios.get("/api/users/logout")
            .then(response => response.data)
            .then(getLoggedInUsername)
            .catch(error => console.error("Error on Logout: " + error.message))

    }


    const handleLoginSubmit = (event: ChangeEvent<HTMLFormElement>) =>{
        event.preventDefault();
        postLoginForm();
        setUsername("");
        setPassword("");
    }

    return <>
        {
            loggedInUsername === 'anonymousUser' ?
                <form onSubmit={handleLoginSubmit}>
                    <label htmlFor={"username"}>Username:</label>
                    <input onChange={(e) => setUsername(e.target.value)} placeholder={"Username"} type="text"/>
                    <label htmlFor={"password"}>Password:</label>
                    <input onChange={(e) => setPassword(e.target.value)} placeholder={"Robert12345!"} type="password"/>
                    <button type={"submit"}>PUSH DE BUTTON</button>
                </form>
                :
                <div>
                    <p>Logged in as {loggedInUsername}</p>
                    <button onClick={handleLogout}>Logout</button>
                </div>
        }
        </>
}