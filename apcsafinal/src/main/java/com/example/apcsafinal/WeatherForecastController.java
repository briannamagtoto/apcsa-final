package com.example.apcsafinal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class WeatherForecastController {

    private final ForecastService forecastService;

    public WeatherForecastController(ForecastService forecastService)
    {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    public String getForecast(Model model, @RequestParam String city)
    {
        if (city != null && !city.isEmpty())
        {
            Forecast forecast = forecastService.getForecast(city);
            Map<String, Map<String, Double>> averageData = forecastService.calculateAverageDailyData(forecast);
            model.addAttribute("forecast", averageData);
        }
        model.addAttribute("currentCity", city);
        return "forecast";
    }
}

