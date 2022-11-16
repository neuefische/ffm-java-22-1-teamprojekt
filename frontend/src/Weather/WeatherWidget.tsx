import React, {useEffect, useState} from 'react';
import axios from "axios";
import DatePicker from "react-datepicker";
import {WeatherModel} from "./WeatherModel";
import "react-datepicker/dist/react-datepicker.css";
import styled from "styled-components";
import {Icon} from '@iconify/react';

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

    return <>
            {weather ?
                <>
                    <StyledWeather>
                        <StyledDiv>
                            <StyledText>
                                <Icon icon="tabler:temperature-minus" width={20}/>&nbsp;&nbsp;{weather.temperature}°C
                            </StyledText>
                        </StyledDiv>
                        <StyledDiv>
                            <StyledText>
                                <Icon icon="mdi:weather-heavy-rain" width={18}/>&ensp;{weather.condition}
                            </StyledText>
                        </StyledDiv>
                        <StyledDiv>
                            <StyledText>
                                <Icon icon="ph:sun-dim-light" width={20}/>&nbsp;&nbsp;{weather.sunshine}%
                            </StyledText>
                        </StyledDiv>
                        <StyledDiv>
                            <StyledText>
                                <Icon icon="ic:round-cloud-queue" width={18}/>&nbsp;&nbsp;{weather.cloud_cover}%
                            </StyledText>
                        </StyledDiv>
                        <StyledDiv>
                            <StyledText>
                                <Icon icon="tabler:wind" width={20}/>&nbsp;&nbsp;{weather.wind_speed}m/s
                            </StyledText>
                        </StyledDiv>
                    </StyledWeather>
                </> :
                <>
                    <StyledText>No weather information retrieved</StyledText>
                </>}
            <StyledDate>
                <DatePicker selected={date} onChange={handleChangeDate} showTimeSelect dateFormat="Pp"
                            onCalendarClose={handleDateSelect}/>
            </StyledDate>
        </>;
}

const StyledWeather = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 10px;
`

const StyledDiv = styled.div`
  width: 75px;
  height: 40px;
`

const StyledDate = styled.div`
  display: flex;
  justify-content: flex-end;
`

const StyledText = styled.p`
  display: flex;
  justify-content: center;
  justify-self: center;
  justify-items: center;
  align-items: center;
  align-self: center;
  align-content: center;
  margin: 5px;
  padding: 2px;
  font-size: 1rem;
`
