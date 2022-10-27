export type WeatherModel = {
    days: {
        dayDate: string
        temperatureMin: number
        temperatureMax: number
        windSpeed: number
        sunshine: number
    }[]
}