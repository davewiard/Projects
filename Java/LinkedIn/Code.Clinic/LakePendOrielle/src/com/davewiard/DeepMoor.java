package com.davewiard;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeepMoor {
    LocalDate date;
    LocalTime time;
    Double airTemp;
    Double barometricPressure;
    Double dewPoint;
    Double relativeHumidity;
    Double windDirection;
    Double windGust;
    Double windSpeed;

    DeepMoor() { }

    public DeepMoor(LocalDate date, LocalTime time, Double airTemp, Double barometricPressure, Double dewPoint, Double relativeHumidity, Double windDirection, Double windGust, Double windSpeed) {
        this.date = date;
        this.time = time;
        this.airTemp = airTemp;
        this.barometricPressure = barometricPressure;
        this.dewPoint = dewPoint;
        this.relativeHumidity = relativeHumidity;
        this.windDirection = windDirection;
        this.windGust = windGust;
        this.windSpeed = windSpeed;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }

    public Double getBarometricPressure() {
        return barometricPressure;
    }

    public void setBarometricPressure(Double barometricPressure) {
        this.barometricPressure = barometricPressure;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
