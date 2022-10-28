import React from 'react';
import './App.css';
import Meals from "./Meals/Meals";
import EmployeeOverview from "./employee/EmployeeOverview";
import PlacementPage from "./Placement/PlacementPage";
import GuestProfile from "./Guest/GuestProfile";
import {HashRouter, Route, Routes} from "react-router-dom";
import RegisterForm from "./Guest/RegisterForm";
import Navigation from "./NavBar/Navigation";

export default function App() {

    return <>
        <h1>Willkommen bei HackDonald's - Ich liebe es!</h1>
        <Meals/>
        <EmployeeOverview/>
        <PlacementPage/>
        <h1>Guests:</h1>
        <HashRouter>
            <Navigation/>
            <Routes>
                <Route path="/GuestProfile" element={<GuestProfile/>}></Route>
                <Route path="/RegisterForm" element={<RegisterForm/>}></Route>
            </Routes>
        </HashRouter>
    </>
}
