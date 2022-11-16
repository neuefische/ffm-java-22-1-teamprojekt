import React from 'react';
import {NavLink} from "react-router-dom";
import {Icon} from '@iconify/react';
import styled from "styled-components";

export default function Navigation() {
    return (
        <nav>
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
        </nav>
    );
}
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
