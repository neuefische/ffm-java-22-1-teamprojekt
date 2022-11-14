import React from 'react';
import {NavLink} from "react-router-dom";

export default function Navigation() {
    return (
        <nav>
            <NavLink to="/GuestProfile">
                <button>GuestList</button>
            </NavLink>
            <NavLink to="/RegisterForm">
                <button>Registration</button>
            </NavLink>
            <NavLink to="/Meals">
                <button>Meals</button>
            </NavLink>
            <NavLink to="/Employees">
                <button>Employees</button>
            </NavLink>
            <NavLink to="/Placements">
                <button>Placements</button>
            </NavLink>
        </nav>
    );
}
