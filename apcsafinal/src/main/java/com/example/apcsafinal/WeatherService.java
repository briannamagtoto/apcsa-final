package com.example.apcsafinal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "be431256710b2a4e9136e50f285b87fa";
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    public Weather getWeather(String city)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Weather> responseEntity = restTemplate.getForEntity(WEATHER_URL, Weather.class, city, API_KEY);
        Weather weather = responseEntity.getBody();

        if (weather != null && weather.getMain() != null) {
            double temperatureInKelvin = weather.getMain().getTemp();
            double temperatureInFahrenheit = convertKelvinToFahrenheit(temperatureInKelvin);
            int roundedTemperatureInFahrenheit = (int) Math.round(temperatureInFahrenheit);
            weather.getMain().setTemp((double) roundedTemperatureInFahrenheit);
        }

        return weather;
    }

    public double convertKelvinToFahrenheit(double temperatureInKelvin)
    {
        double temperatureInCelsius = temperatureInKelvin - 273.15;
        return temperatureInCelsius * 9/5 + 32;
    }
}

