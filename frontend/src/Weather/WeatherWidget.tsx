import React, {useEffect, useState} from 'react';
import axios from "axios";
import DatePicker from "react-datepicker";
import {WeatherModel} from "./WeatherModel";
import "react-datepicker/dist/react-datepicker.css";


export default function WeatherWidget() {

    const [weather, setWeather] = useState<WeatherModel>()
    const [date, setDate] = useState<Date>(new Date())

    const fetchCurrentWeather = () => {
        axios.get("/api/weather/today")
            .then(response => response.data)
            .catch(error => console.log(error))
            .then(data => setWeather(data))
    }

    const fetchWeatherForecast = () => {
        const urlDate = getDate(date)
        const hour = getTime(date)
        axios.get("/api/weather/"+urlDate+"?hour="+hour)
            .then(response => response.data)
            .catch(error => console.log(error))
            .then(data => setWeather(data))
    }

    useEffect(() => {
        fetchCurrentWeather()
    }, [])

    function getDate(date: Date) {
       return date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate();
    }

    function getTime(date: Date) {
        return Number.parseInt(date.toLocaleTimeString("de-DE",{hour: "2-digit"}))
    }

    const handleChangeDate = (date: Date) => {
        setDate(date)
        console.log(date)
    }

    const handleDateSelect = () => {
        fetchWeatherForecast()
    }

    return (
        <div>
            {weather !== undefined ?
                <>
                    <h3>Wetter:</h3>
                    <p>Temperatur: {weather.temperature}°C, Niederschlag: {weather.condition}</p>
                    <p>Sonnenschein: {weather.sunshine}%, Wolken: {weather.cloud_cover}%, Windgeschwindigkeit: {weather.wind_speed}m/s</p>
                </> :
                <>
                    <p>keine Wetterdaten verfügbar</p>
                </>}
            <DatePicker selected={date} onChange={handleChangeDate} showTimeSelect dateFormat="Pp" onCalendarClose={handleDateSelect}/>
        </div>
    );
}