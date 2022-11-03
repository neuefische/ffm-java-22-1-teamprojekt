import React, {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import DatePicker from "react-datepicker";
import {WeatherModel} from "./WeatherModel";
import "react-datepicker/dist/react-datepicker.css";


export default function WeatherWidget() {

    const [weather, setWeather] = useState<WeatherModel>()
    const [date, setDate] = useState<Date>(new Date())

    const fetchWeather = useCallback(() => {
        axios.get("/api/weather/?date=" + date.toISOString())
            .then(response => response.data)
            .catch(error => console.error(error))
            .then(data => setWeather(data))
    }, [date])

    useEffect(() => {
        fetchWeather()
    }, [fetchWeather])


    const handleChangeDate = (date: Date) => {
        setDate(date)
    }

    const handleDateSelect = () => {
        fetchWeather()
    }

    return (
        <div>
            {weather !== undefined ?
                <>
                    <h3>Wetter:</h3>
                    <p>Temperatur: {weather.temperature}°C, Niederschlag: {weather.condition}</p>
                    <p>Sonnenschein: {weather.sunshine}%, Wolken: {weather.cloud_cover}%,
                        Windgeschwindigkeit: {weather.wind_speed}m/s</p>
                </> :
                <>
                    <p>keine Wetterdaten verfügbar</p>
                </>}
            <DatePicker selected={date} onChange={handleChangeDate} showTimeSelect dateFormat="Pp"
                        onCalendarClose={handleDateSelect}/>
        </div>
    );
}