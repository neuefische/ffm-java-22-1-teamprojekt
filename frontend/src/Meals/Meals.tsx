import React, {useEffect, useState} from 'react';
import {MealModel} from "./MealModel";
import axios from "axios";
import {Routes, Route} from 'react-router-dom';
import MealPage from "./MealPage";
import MealBoard from "./MealBoard";
import styled from "styled-components";

export default function Meals() {

    const [meals, setMeals] = useState<MealModel[]>([])

    const fetchAllMeals = () => {
        axios.get("/api/meals")
            .then((response) => response.data)
            .catch((error) => console.log("GET Error: " + error))
            .then(data => setMeals(data))
    }

    useEffect(fetchAllMeals, [])

    return (
        <StyledSection>
            <h2>Meals:</h2>
            <MealBoard fetchAllMeals={fetchAllMeals} meals={meals}/>
            <StyledArticle>
                <Routes>
                    <Route path={"/:id"} element={<MealPage fetchAllMeals={fetchAllMeals} meals={meals}/>}/>
                </Routes>
            </StyledArticle>
        </StyledSection>
    );
}

const StyledSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 10px;
  padding: 8px 20px 25px 20px;
  border: 1px solid rgba(10 10 10 0.3);
  border-radius: 1pc;
  box-shadow: 0 .0625rem .5rem 0 rgba(0, 0, 0, .4), 0 .0625rem .3125rem 0 rgba(0, 0, 0, .4);
`

const StyledArticle = styled.article`
  display: flex;
  flex-direction: column;
  align-items: center;
`
