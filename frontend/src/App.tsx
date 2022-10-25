import React from 'react';
import './App.css';
import Meals from "./Meals/Meals";
import EmployeeOverview from "./employee/EmployeeOverview";

export default function App() {
    return <>
        <h1>Willkommen bei HackDonald's - Ich liebe es!</h1>
        <Meals/>
        <EmployeeOverview />
    </>
}
