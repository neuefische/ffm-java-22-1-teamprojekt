import './App.css';
import Meals from "./Meals/Meals";
import EmployeeOverview from "./Employee/EmployeeOverview";
import PlacementPage from "./Placement/PlacementPage";
import {HashRouter, Route, Routes} from "react-router-dom";
import RegisterForm from "./Guest/RegisterForm";
import Navigation from "./NavBar/Navigation";
import GuestPage from "./Guest/GuestPage";

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

                <Route path="/GuestProfile" element={<GuestPage/>}/>
                <Route path="/RegisterForm" element={<RegisterForm/>}/>
            </Routes>
        </HashRouter>
    </>
}
ç