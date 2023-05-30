package com.example.apcsafinal;
public class Weather {

    private Main main;

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public static class Main
    {

        private Double temp;
        private Double humidity;

        public Double getTemp()
        {
            return temp;
        }

        public void setTemp(Double temp)
        {
            this.temp = temp;
        }

        public Double getHumidity()
        {
            return humidity;
        }

        public void setHumidity(Double humidity)
        {
            this.humidity = humidity;
        }
    }
}


