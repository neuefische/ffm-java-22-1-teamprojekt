import React, {ChangeEvent, FormEvent} from 'react';

function AddPlacementForm() {
    const handleSubmit=(evt: FormEvent<HTMLFormElement>)=>{
    }

    const handleChange=(evt: ChangeEvent<HTMLInputElement>)=>{
    }

    return (
            <form onSubmit={handleSubmit}>
                <input type='text' id='placement-Nr' onChange={handleChange}/>
                <input type='text' id='total-seats' onChange={handleChange}/>
                <button>Add</button>
            </form>
       
    );
}

export default AddPlacementForm;