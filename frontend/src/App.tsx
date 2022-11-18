import React from 'react';
import Meals from "./Meals/Meals";
import EmployeeOverview from "./Employee/EmployeeOverview";
import PlacementPage from "./Placement/PlacementPage";
import {Route, Routes} from "react-router-dom";
import RegisterForm from "./Guest/RegisterForm";
import Navigation from "./NavBar/Navigation";
import WeatherWidget from "./Weather/WeatherWidget";
import GuestPage from "./Guest/GuestPage";
import LoginPage from "./Login/LoginPage";
import styled from "styled-components";

export default function App() {

    return <>
        <StyledHeader>
            <StyledH1AndNav>
                <StyledDiv>
                    <img src={process.env.PUBLIC_URL + '/logo.png'} alt="Logo von Hackdonalds" width="50"/>
                    <h1>Willkommen bei HackDonald's - Ich liebe es!</h1>
                </StyledDiv>
                <StyledNav>
                    <Navigation/>
                </StyledNav>
            </StyledH1AndNav>
            <StyledAside>
                <StyledSection>
                    <WeatherWidget/>
                </StyledSection>
            </StyledAside>
        </StyledHeader>

        <StyledMain>
            <Routes>
                <Route path="/Login" element={<LoginPage/>}/>
                <Route path="/GuestProfile" element={<GuestPage/>}/>
                <Route path="/RegisterForm" element={<RegisterForm/>}/>
                <Route path="/Meals/*" element={<Meals/>}/>
                <Route path="/Employees" element={<EmployeeOverview/>}/>
                <Route path="/Placements" element={<PlacementPage/>}/>
            </Routes>
        </StyledMain>

        <StyledFooter>
            <p>© 2022 HackDonald's</p>
        </StyledFooter>
    </>
}


const StyledHeader = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
  padding: 10px;
  margin-bottom: 20px;
`

const StyledAside = styled.aside`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 5px;
  padding: 5px;
`

const StyledH1AndNav = styled.div`
  display: flex;
  flex-direction: column;
`

const StyledDiv = styled.div`
  display: flex;
  align-items: center;
`

const StyledMain = styled.main`
  margin: 50px;
  min-height: 200px;
`

const StyledNav = styled.nav`
  margin: 10px 0 0 0;
  padding: 10px;
`

const StyledSection = styled.section`
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .2), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .2);
`

const StyledFooter = styled.footer`
  display: flex;
  justify-content: center;
  margin: 10px;
  padding: 10px;
  font-size: 1rem;
`