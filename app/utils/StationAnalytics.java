package utils;

import models.Reading;
import play.Logger;

import java.util.HashMap;
import java.util.List;


public class StationAnalytics {

  public static int getWeatherCode(List<Reading> readings) {
    Reading weatherCode = null;
    if (readings.size() > 0) {
      int lastWeatherCodeIndex = (readings.size() - 1);
      weatherCode = readings.get(lastWeatherCodeIndex);
    }
    Logger.info("StationAnalytics controller invoked. Last Weathercode = " + weatherCode.code);
    return weatherCode.code;
  }

  public static float getlastReadingWind(List<Reading> readings) {
    Reading readingWind = null;
    if (readings.size() > 0) {
      int lastReadingWind = (readings.size() - 1);
      readingWind = readings.get(lastReadingWind);
    }
    Logger.info("StationAnalytics controller invoked. Wind Beaufort = " + readingWind.windSpeed);
    return readingWind.windSpeed;
  }

  public static int getlastReadingPressure(List<Reading> readings) {
    Reading readingPressure = null;
    if (readings.size() > 0) {
      int lastReadingPressure = (readings.size() - 1);
      readingPressure = readings.get(lastReadingPressure);
    }
    Logger.info("StationAnalytics controller invoked. Pressure last Reading = " + readingPressure.pressure);
    return readingPressure.pressure;
  }

  public static double getlastReadingTemperature(List<Reading> readings) {
    Reading readingTemperature = null;
    if (readings.size() > 0) {
      int lastReadingTemperature;
      lastReadingTemperature = (readings.size() - 1);
      readingTemperature = readings.get(lastReadingTemperature);
    }
    Logger.info("StationAnalytics controller invoked. Pressure last Reading = " + readingTemperature.pressure);
    return readingTemperature.temperature;
  }

  public static double celciusToFarenheit(float temperatureCelcius) {
    double farenheitResult = (temperatureCelcius * 9 / 5 + 32);
    double farenheitResultRounded = Math.round(farenheitResult * 100.0) / 100.0;
    return farenheitResultRounded;
  }

  public static String weatherConditionsMap(int weathercode) {
    HashMap<Integer, String> weatherCodeMap;
    weatherCodeMap = new HashMap<Integer, String>();
    weatherCodeMap.put(100, "Clear");
    weatherCodeMap.put(200, "Partial Clouds");
    weatherCodeMap.put(300, "Cloudy");
    weatherCodeMap.put(400, "Light showers");
    weatherCodeMap.put(500, "Heavy showers");
    weatherCodeMap.put(600, "Rain");
    weatherCodeMap.put(700, "Snow");
    weatherCodeMap.put(800, "Thunder");

    String weatherCode = weatherCodeMap.get(weathercode);
    ;
    return weatherCode;
  }

  public static int windDescription(float windSpeed) {
    if (windSpeed == 1) {
      return 1;
    } else if (windSpeed >= 1 & windSpeed <= 5) {
      return 1;
    } else if (windSpeed >= 6 & windSpeed <= 11) {
      return 2;
    } else if (windSpeed >= 12 & windSpeed <= 19) {
      return 3;
    } else if (windSpeed >= 20 & windSpeed <= 28) {
      return 4;
    } else if (windSpeed >= 29 & windSpeed <= 38) {
      return 5;
    } else if (windSpeed >= 39 & windSpeed <= 49) {
      return 6;
    } else if (windSpeed >= 50 & windSpeed <= 61) {
      return 7;
    } else if (windSpeed >= 62 & windSpeed <= 74) {
      return 8;
    } else if (windSpeed >= 75 & windSpeed <= 88) {
      return 9;
    } else if (windSpeed >= 89 & windSpeed <= 102) {
      return 10;
    } else if (windSpeed >= 103 & windSpeed <= 117) {
      return 11;
    } else return 0;
  }

  public static double windChillCalculator(double lastReadingTemperature, float lastReadingWind) {
    double t = lastReadingTemperature;
    double v = lastReadingWind;
    double windChill = 13.12 + 0.6215 * t - (11.37 * Math.pow(v, 0.16)) + ((0.3965 * t) * Math.pow(v, 0.16));
    double windChillResultRounded = Math.round(windChill * 100.0) / 100.0;
    return windChillResultRounded;
  }

  public static String windDirectionCompass(float windDirection) {
    if (windDirection >= 0 & windDirection < 11.25) {
      return "N";
    } else if (windDirection >= 11.25 & windDirection <= 33.75) {
      return "NNE";
    } else if (windDirection >= 33.75 & windDirection <= 56.25) {
      return "NE";
    } else if (windDirection >= 56.25 & windDirection <= 78.75) {
      return "ENE";
    } else if (windDirection >= 78.75 & windDirection <= 101.25) {
      return "E";
    } else if (windDirection >= 101.25 & windDirection <= 123.75) {
      return "ESE";
    } else if (windDirection >= 123.75 & windDirection <= 146.25) {
      return "SE";
    } else if (windDirection >= 146.25 & windDirection <= 168.75) {
      return "SSE";
    } else if (windDirection >= 168.75 & windDirection <= 191.25) {
      return "S";
    } else if (windDirection >= 191.25 & windDirection <= 213.75) {
      return "SSW";
    } else if (windDirection >= 213.75 & windDirection <= 236.25) {
      return "SW";
    } else if (windDirection >= 236.25 & windDirection <= 258.75) {
      return "WSW";
    } else if (windDirection >= 258.75 & windDirection <= 281.25) {
      return "W";
    } else if (windDirection >= 281.25 & windDirection <= 300.75) {
      return "WNW";
    } else if (windDirection >= 303.75 & windDirection <= 326.25) {
      return "NW";
    } else if (windDirection >= 326.25 & windDirection <= 348.75) {
      return "NNW";
    } else return "N/A";
  }

  public static double getMinReadingTemperature(List<Reading> readings) {
    Reading minReadingTemperature = null;
    if (readings.size() > 0) {
      minReadingTemperature = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature < minReadingTemperature.temperature) {
          minReadingTemperature = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Min Temperature Reading = " + minReadingTemperature.temperature);
    return minReadingTemperature.temperature;
  }

  public static double getMaxReadingTemperature(List<Reading> readings) {
    Reading maxReadingTemperature = null;
    if (readings.size() > 0) {
      maxReadingTemperature = readings.get(0);
      for (Reading reading : readings) {
        if (reading.temperature > maxReadingTemperature.temperature) {
          maxReadingTemperature = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Max Temperature Reading = " + maxReadingTemperature.temperature);
    return maxReadingTemperature.temperature;
  }

  public static int getMinReadingPressure(List<Reading> readings) {
    Reading minReadingPressure = null;
    if (readings.size() > 0) {
      minReadingPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure < minReadingPressure.pressure) {
          minReadingPressure = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Min Pressure Reading = " + minReadingPressure.pressure);
    return minReadingPressure.pressure;
  }

  public static int getMaxReadingPressure(List<Reading> readings) {
    Reading maxReadingPressure = null;
    if (readings.size() > 0) {
      maxReadingPressure = readings.get(0);
      for (Reading reading : readings) {
        if (reading.pressure > maxReadingPressure.pressure) {
          maxReadingPressure = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Max Pressure Reading = " + maxReadingPressure.pressure);
    return maxReadingPressure.pressure;
  }

  public static float getMinWindSpeed(List<Reading> readings) {
    Reading minWindSpeed = null;
    if (readings.size() > 0) {
      minWindSpeed = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed < minWindSpeed.windSpeed) {
          minWindSpeed = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Min Wind Speed Reading = " + minWindSpeed.windSpeed);
    return minWindSpeed.windSpeed;
  }

  public static float getMaxWindSpeed(List<Reading> readings) {
    Reading maxWindSpeed = null;
    if (readings.size() > 0) {
      maxWindSpeed = readings.get(0);
      for (Reading reading : readings) {
        if (reading.windSpeed > maxWindSpeed.windSpeed) {
          maxWindSpeed = reading;
        }
      }
    }
    Logger.info("StationAnalytics controller invoked. Max Wind Speed Reading = " + maxWindSpeed.windSpeed);
    return maxWindSpeed.windSpeed;
  }

  public static String weatherCodeIconMap(int weathercode) {
    HashMap<Integer, String> weatherCodeIconMap;
    weatherCodeIconMap = new HashMap<Integer, String>();
    weatherCodeIconMap.put(100, "yellow sun icon");
    weatherCodeIconMap.put(200, "cloud sun icon");
    weatherCodeIconMap.put(300, "cloud rain icon");
    weatherCodeIconMap.put(400, "cloud sun rain icon");
    weatherCodeIconMap.put(500, "cloud showers heavy icon");
    weatherCodeIconMap.put(600, "cloud rain icon");
    weatherCodeIconMap.put(700, "snowflake icon");
    weatherCodeIconMap.put(800, "bolt icon");

    String weatherCodeIcon = weatherCodeIconMap.get(weathercode);
    ;
    return weatherCodeIcon;
  }
}

