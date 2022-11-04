import React from 'react';
import {NavLink} from "react-router-dom";
import {Icon} from '@iconify/react';
import styled from "styled-components";

export default function Navigation() {
    return (
        <StyledNav>
            <NavLink to="/Login">
                <button>Login</button>
            </NavLink>
            <NavLink to="/GuestProfile">
                <StyledButton>
                    <Icon icon="mdi:user"/> GuestList
                </StyledButton>
            </NavLink>

            <NavLink to="/RegisterForm">
                <StyledButton>
                    <Icon icon="mdi:register"/> Registration
                </StyledButton>
            </NavLink>
            <NavLink to="/Meals">
                <StyledButton>
                    <Icon icon="healthicons:hot-meal"/> Meals
                </StyledButton>
            </NavLink>
            <NavLink to="/Placements">
                <StyledButton>
                    <Icon icon="material-symbols:table-bar"/> Placements
                </StyledButton>
            </NavLink>
            <NavLink to="/Employees">
                <StyledButton>
                    <Icon icon="clarity:employee-group-solid"/> Employees
                </StyledButton>
            </NavLink>

        </StyledNav>
    );
}

const StyledNav = styled.nav`
  display: flex;
  justify-content: space-between;
  margin: 10px;
  padding: 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .2), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .2);
`

const StyledButton = styled.button`
  padding: 8px 10px;
  font-size: 1rem;
  cursor: pointer;
  margin: 3px;
  transition-duration: 0.4s;
  background-color: var(--color-button-background);
  color: var(--color-text);
  border: none;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  border-radius: 5px;

  &:hover {
    background-color: var(--color-button-hover);
  }

  &:active {
    background-color: var(--color-button-active);
  }
`;
