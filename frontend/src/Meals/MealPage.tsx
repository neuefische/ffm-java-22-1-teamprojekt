import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import {NavLink, useParams} from "react-router-dom";

import axios from "axios";
import {MealModel} from "./MealModel";

type MealPageProps = {
    fetchAllMeals: () => void,
    meals: MealModel[]
}

function MealPage(props: MealPageProps) {
    const id = useParams().id
    const [newMealName, setNewMealName] = useState<string>("")

    // if(!id) {
    //     return <div>ID Error</div>
    // }

    //const mealToDisplay: MealModel= props.meals.filter(element => element.id === id)[0];
    const mealToDisplay = props.meals.find(element => element.id === id)
    //console.log("mealToDisplay" + mealToDisplay.name);

/*    if(!mealToDisplay){
        return <div>mealToDisplay Error</div>
    }*/

    useEffect(()=>{
        setNewMealName(mealToDisplay!.name)
    },[])







    const handleEditMealSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        axios.put("/api/meals/"+id, {
            "id": id,
            "name": newMealName
            })
            .catch((error) => console.log("PUT Error: " + error))
            .then(props.fetchAllMeals)
        setNewMealName("")
    }

    const handleNewMealName = (event: ChangeEvent<HTMLInputElement>) => {
        setNewMealName(event.target.value)
    }

    return (
        <section>
            <div>
                <NavLink to={"/"}>Back</NavLink>
                <h3>{mealToDisplay!.name}</h3>
                <p>ID: {id}</p>
            </div>
            <form onSubmit={handleEditMealSubmit}>
                <input value={newMealName} onChange={handleNewMealName}/>
                <button type={"submit"}>Update Meal</button>
            </form>
        </section>

    );
}

export default MealPage;