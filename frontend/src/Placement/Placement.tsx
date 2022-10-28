import React, {ChangeEvent, FormEvent, useEffect, useState} from 'react';
import PlacementModel from "./PlacementModel";
import axios from "axios";


type PlacementProps = {
    singlePlacement: PlacementModel,
    fetchAll: () => void
}

function Placement(props: PlacementProps) {
    const [newValue, setNewValue] = useState<number>(0)
    const [editMode, setEditMode] = useState<boolean>(false)

    


    const updatePlacement = () => {
        axios
            .put("api/placements/" + props.singlePlacement.id, {totalSeats: newValue})
            .then(() => {
                props.fetchAll()
                setEditMode(false)
            })
            .catch(err => console.log(err))
    }

    const deletePlacement = () => {
        axios
            .delete("api/placements/" + props.singlePlacement.id)
            .then(() => {
                props.fetchAll()
            })
            .catch(err => console.log(err))
    }

    useEffect(() => {
        setNewValue(props.singlePlacement.totalSeats)
    }, [])


    const handleSubmit = (evt: FormEvent<HTMLFormElement>) => {
        evt.preventDefault()
        updatePlacement()
        // axios.put
    }

    const handleUpdateTotalSeats = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value)
            setNewValue(parseInt(evt.target.value))
    }

    const handleDelete = () => {
        console.log("delete placement Nr: ", props.singlePlacement.placementNr, " id: ", props.singlePlacement.id)
        deletePlacement();
    }

    return (
        <li>
            <h3>Table-Nr: {props.singlePlacement.placementNr}</h3>
            <p>Total Seats: {props.singlePlacement.totalSeats}</p>
            <button onClick={() => setEditMode(!editMode)}>update Seats Number</button>
            <button onClick={handleDelete}>Delete</button>
            {editMode && <form onSubmit={handleSubmit}>
                <input min={2} max={15} type='number' onChange={handleUpdateTotalSeats} value={newValue}/>
                <button type='submit'>Edit</button>
            </form>}

        </li>
    );
}

export default Placement;