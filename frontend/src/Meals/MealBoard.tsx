import React, {ChangeEvent, FormEvent, useState} from 'react';
import {MealModel} from "./MealModel";
import {Link} from "react-router-dom";
import axios from "axios";

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


    return (<>
            <ul>
                {
                    props.meals.map(meal => {
                        return (
                            <li key={meal.id}>
                                <Link to={"/Meals/" + meal.id}>{meal.name}</Link>
                            </li>
                        )
                    })
                }
            </ul>
            <form onSubmit={handleAddMealSubmit}>
                <input required value={mealName} onChange={handleNewMealName}/>
                <button type={"submit"}>Add Meal</button>
            </form>
        </>
    )
        ;
}