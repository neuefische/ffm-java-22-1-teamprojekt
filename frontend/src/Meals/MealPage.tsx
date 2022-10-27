import React from 'react';
import {NavLink, useParams} from "react-router-dom";

import {MealModel} from "./MealModel";
import UpdateMeal from "./UpdateMeal";

type MealPageProps = {
    fetchAllMeals: () => void,
    meals: MealModel[]
}

function MealPage(props: MealPageProps) {
    const id = useParams().id

    if(!id) {
        return <div>ID Error</div>
    }

    const mealToDisplay = props.meals.find(element => element.id === id)

    if(!mealToDisplay){
        return <div>mealToDisplay Error</div>
    }

    return (
        <section>
            <div>
                <NavLink onClick={props.fetchAllMeals} to={"/"}>Back</NavLink>
                <h3>{mealToDisplay.name}</h3>
                <p>ID: {id}</p>
            </div>
            <UpdateMeal meal={mealToDisplay} fetchAllMeals={props.fetchAllMeals}/>
        </section>
    );
}

export default MealPage;