import React, {useEffect, useState} from 'react';
import axios from "axios";
import {WeatherModel} from "./WeatherModel";


export default function WeatherWidget() {

    const [weather, setWeather] = useState<WeatherModel>()

    const fetchWeather = () => {
        axios.get("/api/weather/today")
            .then(response => response.data)
            .catch(error => console.log(error))
            .then(data => setWeather(data))

    }

    useEffect(() => {
        fetchWeather()
    }, [])


    return (
        <div>
            {weather !== undefined && <>
                <h3>Wetter heute:</h3>
                <p>Temperatur: {weather.temperature}°C, {weather.condition}</p>
            </>}
        </div>
    );
}