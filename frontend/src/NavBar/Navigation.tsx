import React from 'react';
import { NavLink} from "react-router-dom";

function Navigation() {
    return (
        <nav>
            <NavLink to="/GuestProfile">
                <button>HomePage</button>
            </NavLink>
            <NavLink to="/RegisterForm">
                <button>Registration</button>
            </NavLink>
        </nav>
    );
}

export default Navigation;