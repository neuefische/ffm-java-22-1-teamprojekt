type updateEmployeeProps = {
        closeModal: () => void
}

export default function EmployeeModal(props:updateEmployeeProps){

    return (
        <div>
            <form>
                <input type="text"></input>
            <button type="button">Update</button>
            </form>
            <button onClick={props.closeModal}>Close</button>

        </div>
    );
}