import React, {ChangeEvent, FormEvent, useState} from 'react';
import {MealModel} from "./MealModel";
import {Link} from "react-router-dom";
import axios from "axios";
import styled from "styled-components";

type MealBoardProps = {
    fetchAllMeals: () => void,
    meals: MealModel[]
}

export default function MealBoard(props: MealBoardProps) {
    const [mealName, setMealName] = useState<string>("")

    const handleNewMealName = (event: ChangeEvent<HTMLInputElement>) => {
        setMealName(event.target.value)
    }

    const handleAddMealSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.post("/api/meals", {
            name: mealName
        })
            .catch((error) => console.log("POST Error: " + error))
            .then(props.fetchAllMeals)
        setMealName("")
    }

    return <>
        <StyledUl>
            {
                props.meals.map(meal => {
                    return <>
                        <Link to={"/Meals/" + meal.id}><StyledLi key={meal.id}>
                            {meal.name}
                        </StyledLi></Link>
                    </>
                })
            }
        </StyledUl>
        <StyledForm onSubmit={handleAddMealSubmit}>
            <StyledInput required value={mealName} onChange={handleNewMealName}/>
            <StyledButton type={"submit"}>Add Meal</StyledButton>
        </StyledForm>
    </>;
}

const StyledUl = styled.ul`
  padding: 0;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`

const StyledLi = styled.li`
  font-size: 1rem;
  margin: 10px;
  padding: 10px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`

const StyledForm = styled.form`
  display: flex;
  justify-content: right;
  margin: 20px;
`

const StyledInput = styled.input`
  margin: 3px;
  padding: 3px;
  border-radius: 5px;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .04), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .04);
`;

const StyledButton = styled.button`
  padding: 8px 10px;
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
