import React, {useEffect, useState} from 'react';
import {MealModel} from "./MealModel";
import axios from "axios";




export default function Meals() {

    const[meals, setMeals] = useState<MealModel[]>([]);

    const fetchAllMeals = () => {
        axios.get("/api/meals")
            .then((response) => response.data)
            .catch((error) => console.log("GET Error: " + error))
            .then(data => setMeals(data))
    }


    useEffect(fetchAllMeals, [])


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
            <form>
                <input/>
                <button>Add Meal</button>
            </form>
        </section>
    );
}

