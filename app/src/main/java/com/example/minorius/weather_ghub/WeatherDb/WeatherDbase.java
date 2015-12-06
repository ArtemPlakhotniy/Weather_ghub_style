package com.example.minorius.weather_ghub.WeatherDb;

import io.realm.RealmObject;

/**
 * Created by Minorius on 29.11.2015.
 */
public class WeatherDbase extends RealmObject {

    private String dataInDb;
    private String tempInDb;
    private String conditionsInDb;
    private String windSpeedInDb;
    private String directionInDb;
    private String humidityInDb;
    private String iconInDb;

    public String getDataInDb() {
        return dataInDb;
    }

    public void setDataInDb(String dataInDb) {
        this.dataInDb = dataInDb;
    }

    public String getTempInDb() {
        return tempInDb;
    }

    public void setTempInDb(String tempInDb) {
        this.tempInDb = tempInDb;
    }

    public String getConditionsInDb() {
        return conditionsInDb;
    }

    public void setConditionsInDb(String conditionsInDb) {
        this.conditionsInDb = conditionsInDb;
    }

    public String getWindSpeedInDb() {
        return windSpeedInDb;
    }

    public void setWindSpeedInDb(String windSpeedInDb) {
        this.windSpeedInDb = windSpeedInDb;
    }

    public String getDirectionInDb() {
        return directionInDb;
    }

    public void setDirectionInDb(String directionInDb) {
        this.directionInDb = directionInDb;
    }

    public String getHumidityInDb() {
        return humidityInDb;
    }

    public void setHumidityInDb(String humidityInDb) {
        this.humidityInDb = humidityInDb;
    }

    public String getIconInDb() {
        return iconInDb;
    }

    public void setIconInDb(String iconInDb) {
        this.iconInDb = iconInDb;
    }
}
