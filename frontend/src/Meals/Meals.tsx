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



    return (
        <section>
            <h1>Meals</h1>
            <HashRouter>
                <Routes>
                    <Route path={"/"} element={<MealBoard fetchAllMeals={fetchAllMeals} meals={meals}/>}/>
                    <Route path={"/:id"} element={<MealPage fetchAllMeals={fetchAllMeals} meals={meals}/>}/>
                </Routes>
            </HashRouter>

        </section>
    );
}

