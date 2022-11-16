import React, {ChangeEvent, FormEvent, useState} from 'react';
import axios from "axios";
import {MealModel} from "./MealModel";
import {useNavigate} from "react-router-dom";
import styled from "styled-components";

type UpdateMealProps = {
    meal: MealModel
    fetchAllMeals: () => void
}

export default function UpdateMeal(props: UpdateMealProps) {

    const [newMealName, setNewMealName] = useState<string>(props.meal.name)
    const navigate = useNavigate()

    const handleEditMealSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/meals/" + props.meal.id, {
            "id": props.meal.id,
            "name": newMealName
        })
            .catch((error) => console.log("PUT Error: " + error))
            .then(props.fetchAllMeals)
        setNewMealName("")
        navigate("/Meals/")
    }

    const handleNewMealName = (event: ChangeEvent<HTMLInputElement>) => {
        setNewMealName(event.target.value)
    }

    return (
        <form onSubmit={handleEditMealSubmit}>
            <StyledInput required value={newMealName} onChange={handleNewMealName}/>
            <StyledButton type={"submit"}>Update Meal</StyledButton>
        </form>
    );
}

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledButton = styled.button`
  padding: 6px 10px;
  font-size: 0.8rem;
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
