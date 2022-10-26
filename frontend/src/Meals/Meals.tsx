import React, {useEffect, useState} from 'react';
import {MealModel} from "./MealModel";
import axios from "axios";
import {HashRouter, Routes, Route} from 'react-router-dom';
import MealPage from "./MealPage";
import MealBoard from "./MealBoard";




export default function Meals() {

    const[meals, setMeals] = useState<MealModel[]>([])

    const fetchAllMeals = () => {
        axios.get("/api/meals")
            .then((response) => response.data)
            .catch((error) => console.log("GET Error: " + error))
            .then(data => setMeals(data))
    }

    useEffect(fetchAllMeals, [])

    const handleNewMealName = (event: ChangeEvent<HTMLInputElement>) => {
        setMealName(event.target.value)
    }

    const handleAddMealSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.post("/api/meals", {
            name: mealName
            })
            .catch((error) => console.log("POST Error: " + error))
        setMealName("")
    }

    return (
        <section>
            <h1>Meals</h1>
            <ul>
                {
                    meals.map(meal => {
                        return <li>{meal.name}</li>
                    })
                }
            </ul>
            <form onSubmit={handleAddMealSubmit}>
                <input value={mealName} onChange={handleNewMealName}/>
                <button type={"submit"}>Add Meal</button>
            </form>
        </section>
    );
}

