package com.example.apcsafinal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastService extends WeatherService
{
    private static final String API_KEY = "be431256710b2a4e9136e50f285b87fa";
    private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}";

    public Forecast getForecast(String city)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Forecast> responseEntity = restTemplate.getForEntity(FORECAST_URL, Forecast.class, city, API_KEY);
        return responseEntity.getBody();
    }

    public Map<String, Map<String, Double>> calculateAverageDailyData(Forecast forecast)
    {
        Map<String, List<Double>> dataByDay = new HashMap<>();
        Map<String, Map<String, Double>> averageDataByDay = new HashMap<>();

        if (forecast != null && forecast.getList() != null)
        {
            for (Forecast.WeatherForecast weatherForecast : forecast.getList())
            {
                String date = weatherForecast.getDt_txt().split(" ")[0];
                double tempInFahrenheit = super.convertKelvinToFahrenheit(weatherForecast.getMain().getTemp());
                double humidity = weatherForecast.getMain().getHumidity();

                if (!dataByDay.containsKey(date))
                {
                    dataByDay.put(date, new ArrayList<>());
                }
                dataByDay.get(date).add(tempInFahrenheit);
                dataByDay.get(date).add(humidity);
            }

            for (String date : dataByDay.keySet())
            {
                List<Double> data = dataByDay.get(date);
                double totalTemp = 0;
                double totalHumidity = 0;

                for (int i = 0; i < data.size(); i += 2)
                {
                    totalTemp += data.get(i);
                    totalHumidity += data.get(i + 1);
                }

                double averageTemp = totalTemp / (data.size() / 2);
                double averageHumidity = totalHumidity / (data.size() / 2);

                Map<String, Double> averageData = new HashMap<>();
                averageData.put("averageTemp", (double) Math.round(averageTemp));
                averageData.put("averageHumidity", (double) Math.round(averageHumidity));

                averageDataByDay.put(date, averageData);
            }
        }

        return averageDataByDay;
    }
}
