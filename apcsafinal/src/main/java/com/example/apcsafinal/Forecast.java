package com.example.apcsafinal;
import java.util.ArrayList;
import java.util.List;

public class Forecast
{
    private List<WeatherForecast> list = new ArrayList<>();

    public List<WeatherForecast> getList()
    {
        return list;
    }

    public void setList(List<WeatherForecast> list)
    {
        this.list = list;
    }

    public static class WeatherForecast extends Weather
    {
        private String dt_txt;

        public String getDt_txt()
        {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt)
        {
            this.dt_txt = dt_txt;
        }
    }
}

