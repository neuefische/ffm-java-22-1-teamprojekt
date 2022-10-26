import React, {ChangeEvent, FormEvent, useState} from 'react';
import axios from "axios";
import {MealModel} from "./MealModel";
import {useNavigate} from "react-router-dom";

type UpdateMealProps = {
    meal: MealModel
    fetchAllMeals: () => void
}

function UpdateMeal(props: UpdateMealProps) {

    const [newMealName, setNewMealName] = useState<string>(props.meal.name)
    const navigate = useNavigate()

    const handleEditMealSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/meals/"+props.meal.id, {
            "id": props.meal.id,
            "name": newMealName
        })
            .catch((error) => console.log("PUT Error: " + error))
            .then(props.fetchAllMeals)
        setNewMealName("")
        navigate("/")
    }

    const handleNewMealName = (event: ChangeEvent<HTMLInputElement>) => {
        setNewMealName(event.target.value)
    }

    return (
        <form onSubmit={handleEditMealSubmit}>
            <input value={newMealName} onChange={handleNewMealName}/>
            <button type={"submit"}>Update Meal</button>
        </form>
    );
}

export default UpdateMeal;