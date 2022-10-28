import React, {useState} from 'react';
import "../styles/TaskCard.css"
import GuestModel{GuestModel} from "../Model/TaskModel";
import TaskModal from "./TaskModal";
import axios from "axios";
import {AiOutlineArrowRight, AiOutlineArrowLeft} from "react-icons/ai"
import {RiDeleteBin6Line} from "react-icons/ri"


type TaskCardProps = {
    task: TaskModel
    fetchAllTasks: () => void
}

export default function TaskCard(props: TaskCardProps) {
    const [editModal, setEditModal] = useState(false)

    const handleEdit = () => {
        setEditModal(!editModal)
    }

    const handleDelete = () => {
        axios.delete("/api/todo/" + props.task.id)
            .then(() => {
                props.fetchAllTasks()
            })
            .catch(error => {
                console.log("[DELETE ERROR:]" + error)
            })
    }

    const closeModal = () => {
        setEditModal(false)
    }


    function handleAdvance() {
        axios.put("/api/todo/" + props.task.id, {
            id: props.task.id,
            description: props.task.description,
            status: props.task.status === 'OPEN' ? 'IN_PROGRESS' : 'DONE',
        })
            .then(response => {
                props.fetchAllTasks()
            })
            .catch(error => console.log(error))
    }

    function handleBackwards() {
        axios.put("/api/todo/" + props.task.id, {
            id: props.task.id,
            description: props.task.description,
            status: props.task.status === 'DONE' ? 'IN_PROGRESS' : 'OPEN',
        })
            .then(response => {
                props.fetchAllTasks()
            })
            .catch(error => console.log(error))
    }
