import React, {useEffect, useState} from 'react';
import axios from "axios";
import DatePicker from "react-datepicker";
import {WeatherModel} from "./WeatherModel";
import "react-datepicker/dist/react-datepicker.css";
import styled from "styled-components";


export default function WeatherWidget() {

    const [weather, setWeather] = useState<WeatherModel>()
    const [date, setDate] = useState<Date>(new Date())

    const fetchWeather = () => {
        axios.get("/api/weather/?date=" + date.toISOString())
            .then(response => response.data)
            .catch(error => console.error(error))
            .then(data => setWeather(data))
    }

    useEffect(() => {
        fetchWeather()
        // eslint-disable-next-line
    }, [])

    const handleChangeDate = (date: Date) => {
        setDate(date)
    }

    const handleDateSelect = () => {
        fetchWeather()
    }

    return (
        <div>
            {weather ?
                <>
                    <h3>Weather:</h3>
                    <StyledText>Temperature: {weather.temperature}°C</StyledText>
                        <StyledText>Rainfall: {weather.condition}</StyledText>
                    <StyledText>Sunshine: {weather.sunshine}%</StyledText>
                        <StyledText>Clouds: {weather.cloud_cover}%</StyledText>
                    <StyledText>Wind: {weather.wind_speed}m/s</StyledText>
                </> :
                <>
                    <StyledText>No weather information retrieved</StyledText>
                </>}
            <DatePicker selected={date} onChange={handleChangeDate} showTimeSelect dateFormat="Pp"
                        onCalendarClose={handleDateSelect}/>
        </div>
    );
}

const StyledText = styled.p`
  margin: 0;
  padding: 2px;
  font-size: 0.9rem;
`
