import React from 'react';
import {NavLink, useNavigate, useParams} from "react-router-dom";

import {MealModel} from "./MealModel";
import UpdateMeal from "./UpdateMeal";
import axios from "axios";
import styled from "styled-components";

type MealPageProps = {
    fetchAllMeals: () => void,
    meals: MealModel[]
}

export default function MealPage(props: MealPageProps) {
    const id = useParams().id
    const navigate = useNavigate()

    if (!id) {
        return <div>ID Error</div>
    }

    const mealToDisplay = props.meals.find(element => element.id === id)

    if (!mealToDisplay) {
        return <div>mealToDisplay Error</div>
    }

    const handleDelete = () => {
        axios.delete("/api/meals/" + id)
            .catch(error => console.log("DELETE Error: " + error))
            .then(() => alert("Eintrag " + mealToDisplay.name + " gelöscht!"))
            .then(props.fetchAllMeals)
        navigate("/Meals/")
    }

    return (
        <StyledSection>
            <StyledName>{mealToDisplay.name}</StyledName>
            <StyledId>ID: {id}</StyledId>
            <UpdateMeal meal={mealToDisplay} fetchAllMeals={props.fetchAllMeals}/>
            <StyledDivButtons>
                <StyledButton onClick={handleDelete}>Delete Meal</StyledButton>
                <NavLink onClick={props.fetchAllMeals} to={"/Meals/"}>
                    <StyledButton>Cancel</StyledButton>
                </NavLink>
            </StyledDivButtons>
        </StyledSection>
    );
}

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 30px 10px;
  width: 20vw;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`

const StyledDivButtons = styled.div`
  display: flex;
  justify-content: center;
  margin: 10px;
`

const StyledButton = styled.button`
  margin: 3px;
  padding: 5px;
  width: 100px;
  cursor: pointer;
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

const StyledId = styled.p`
  padding: 4px 0 0 4px;
  font-size: 0.85rem;
`

const StyledName = styled.p`
  margin-bottom: 5px;
  padding: 4px;
  font-size: 1.1rem;
`
