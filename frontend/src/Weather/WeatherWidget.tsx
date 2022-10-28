import React, {useEffect, useState} from 'react';
import axios from "axios";
import {WeatherModel} from "./WeatherModel";


export default function WeatherWidget() {
    axios.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8';
    axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';
    // const apiUrl: string = "https://app-prod-ws.warnwetter.de/v30/stationOverviewExtended?stationIds=10637"

    const apiUrl2: string = "https://api.brightsky.dev/current_weather?wmo_station_id=10637"


    const [weather, setWeather] = useState<WeatherModel>()

    const getWeather = () => {
        axios.get(apiUrl2, {})
            .then(response => response.data)
            .catch(error => console.log(error))
            //.then(data => setWeather(data))
            .then(data => console.log(data))
    }

    useEffect(() => {
        fetchWeather()
    }, [])

    return (

        <div>{}</div>
    );
}