import React, {ChangeEvent, FormEvent, useState} from 'react';
import axios from 'axios';
import PlacementModel from "./PlacementModel";

type AddPlacementFormProps = {
    fetchAll: () => void;
}

function AddPlacementForm(props: AddPlacementFormProps) {
    const [tableNumber, setTableNumber] = useState<string>('')
    const [totalSeats, setTotalSeats] = useState<string>('')


    const postNewPlacement = (newPlacement: Omit<PlacementModel, 'id'>) => {
        axios.post("api/placements", newPlacement)
            .then(() => {
                props.fetchAll();
                setTableNumber("")
                setTotalSeats("")
            })
            .catch((error) => {
                console.log(error)
            })

    }


    const handleSubmit = (evt: FormEvent<HTMLFormElement>) => {
        evt.preventDefault();
        console.log('[Submit Form] => ', 'Table number: ' + tableNumber, ' Total seats: ' + totalSeats)
        postNewPlacement({placementNr: parseInt(tableNumber), totalSeats: parseInt(totalSeats)})

    }

    const handleTableNumberChange = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value)
            setTableNumber(evt.target.value)
    }
    const handleTotalSeatsChange = (evt: ChangeEvent<HTMLInputElement>) => {
        if (evt.target.value) setTotalSeats(evt.target.value)
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor='placement-Nr'>Table Nr.: </label>
                <input type='text' id='placement-Nr' onChange={handleTableNumberChange} value={tableNumber}/>
            </div>
            <div>
                <label htmlFor='total-seats'>Total Seats: </label>
                <input type='text' id='total-seats' onChange={handleTotalSeatsChange} value={totalSeats}/>
            </div>
            <button>Add</button>
        </form>

    );
}

export default AddPlacementForm;