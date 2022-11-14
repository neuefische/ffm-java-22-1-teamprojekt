import React from 'react';
import './App.css';
import Meals from "./Meals/Meals";
import EmployeeOverview from "./Employee/EmployeeOverview";
import PlacementPage from "./Placement/PlacementPage";
import {Route, Routes} from "react-router-dom";
import RegisterForm from "./Guest/RegisterForm";
import Navigation from "./NavBar/Navigation";
import WeatherWidget from "./Weather/WeatherWidget";
import GuestPage from "./Guest/GuestPage";

export default function App() {

    return <>

        <header>
            <h1>Willkommen bei HackDonald's - Ich liebe es!</h1>
            <WeatherWidget/>
            <Navigation/>
        </header>

        <main>
            <Routes>
                <Route path="/GuestProfile" element={<GuestPage/>}/>

                <Route path="/RegisterForm" element={<RegisterForm/>}/>

                <Route path="/Meals/*" element={<Meals/>}/>

                <Route path="/Employees" element={<EmployeeOverview/>}/>

                <Route path="/Placements" element={<PlacementPage/>}/>
            </Routes>
        </main>

        <footer>
            ICH BIN FOOT er
        </footer>
    </>
}
