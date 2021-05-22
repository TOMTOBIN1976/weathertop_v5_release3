package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

import java.util.HashMap; // import the HashMap class

import utils.StationAnalytics;

import java.util.List;
import java.util.Date;

public class StationCtrl extends Controller {
  public static void index(Long id) {
    Station station = Station.findById(id);
//===================================================================================
// Check if there is any readings (prevent null pointers!), if not just render

    List<Reading> readings = station.readings;
    if (readings.size() == 0) {
      String message = "No entries in the arraylist";
      render("stationlist.html", station);
    }
//====================================================================================

    Logger.info("StationCtrl controller invoked. Station = " + id);

    int lastWeathercode = StationAnalytics.getWeatherCode(station.readings);
    double lastReadingTemperature = StationAnalytics.getlastReadingTemperature(station.readings); //Temperature in C (default temperature)
    float lastReadingWind = StationAnalytics.getlastReadingWind(station.readings); //Wind speed
    String weatherCondition = StationAnalytics.weatherConditionsMap(lastWeathercode);  //Weather conditions - code presented as a description ap per Appendix A
    int lastReadingPressure = StationAnalytics.getlastReadingPressure(station.readings); //Pressure (as read in)
    double fahrenheitTemperature = StationAnalytics.celciusToFarenheit((float) lastReadingTemperature);  //Temperature in F (default temperature)
    float windBeaufort = StationAnalytics.windDescription(lastReadingWind);  //Wind in Beaufort as per Appendix A (iii)
    String windDirectionCompass = StationAnalytics.windDirectionCompass(lastReadingWind); //Wind Compass as per Appendix A (iv
    double lastReadingWindChill = StationAnalytics.windChillCalculator(lastReadingTemperature, lastReadingWind);  //Wind Chill as per Appendix A (v)
    double stationlatitude = station.getLat(); //Station latitude
    double stationlongitude = station.getLng(); //Station longitude
    double minimumReadingTemperature = StationAnalytics.getMinReadingTemperature(station.readings); //Min temperature
    double maximumReadingTemperature = StationAnalytics.getMaxReadingTemperature(station.readings); //Max temperature
    int minimumReadingPressure = StationAnalytics.getMinReadingPressure(station.readings); //Min Pressure
    int maximumReadingPressure = StationAnalytics.getMaxReadingPressure(station.readings); //Max pressure
    float minimumWindSpeed = StationAnalytics.getMinWindSpeed(station.readings); //Min Wind Speed
    float maximumWindSpeed = StationAnalytics.getMaxWindSpeed(station.readings); //Min Wind Speed
    String weatherCodeIcon = StationAnalytics.weatherCodeIconMap(lastWeathercode); //latested weather icon

    render("stationlist.html", station, lastReadingPressure, lastWeathercode, lastReadingWind, lastReadingTemperature, weatherCondition, windBeaufort, fahrenheitTemperature, lastReadingWindChill, windDirectionCompass, stationlatitude, stationlongitude, minimumReadingTemperature, maximumReadingTemperature, maximumReadingPressure, minimumReadingPressure, minimumWindSpeed, maximumWindSpeed, weatherCodeIcon);
  }

  public static void addReading(Long id, int code, float temperature, float windSpeed, int pressure, int windDirection, Date dateTime) {
    dateTime = new Date();
    Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, dateTime);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/station/" + id);
  }


  public HashMap<String, String> getWindDirection() {
    HashMap<String, String> windDirection = new HashMap<String, String>();
    windDirection.put("N", "348.75 - 11.25");
    windDirection.put("NNE", "11.25 - 33.75");
    windDirection.put("NE", "33.75 - 56.25");
    windDirection.put("ENE", "56.25 - 78.75");
    windDirection.put("E", "78.75 - 101.25");
    windDirection.put("ESE", "101.25 - 123.75");
    windDirection.put("SE", "123.75 - 146.25");
    windDirection.put("S", "168.75 - 191.25");
    windDirection.put("SSW", "191.25 - 213.75");
    windDirection.put("SW", "213.75 - 236.25");
    windDirection.put("WSW", "236.25 - 258.75");
    windDirection.put("W", "258.75 - 281.25");
    windDirection.put("WNW", "281.25 - 303.75");
    windDirection.put("NW", "303.75 - 326.25");
    windDirection.put("NNW", "326.25 - 348.75");
    return windDirection;
  }

  public static void deletereading(Long id, Long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing " + reading.id);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    render("stationlist.html", station);
  }

  ;
}



