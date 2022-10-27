import React from 'react';
import {NavLink, useNavigate, useParams} from "react-router-dom";

import {MealModel} from "./MealModel";
import UpdateMeal from "./UpdateMeal";
import axios from "axios";

type MealPageProps = {
    fetchAllMeals: () => void,
    meals: MealModel[]
}

function MealPage(props: MealPageProps) {
    const id = useParams().id
    const navigate = useNavigate()

    if(!id) {
        return <div>ID Error</div>
    }

    const mealToDisplay = props.meals.find(element => element.id === id)

    if(!mealToDisplay){
        return <div>mealToDisplay Error</div>
    }

    const handleDelete = () => {
        axios.delete("/api/meals/"+id)
            .catch(error => console.log("DELETE Error: "+error))
            .then(() => alert("Eintrag "+mealToDisplay.name+ " gelöscht!"))
            .then(props.fetchAllMeals)
        navigate("/")
    }

    return (
        <section>
            <div>
                <NavLink onClick={props.fetchAllMeals} to={"/"}>Back</NavLink>
                <h3>{mealToDisplay.name}</h3>
                <p>ID: {id}</p>
            </div>
            <UpdateMeal meal={mealToDisplay} fetchAllMeals={props.fetchAllMeals}/>
            <button onClick={handleDelete}>Delete Meal</button>
        </section>
    );
}

export default MealPage;