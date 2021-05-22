package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity

public class Station extends Model {
  public String name;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();
  public double lat;
  public double lng;

  public Station(String name, double lat, double lng) {
    this.name = name;
    this.lat = lat;
    this.lng = lng;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<Reading> getReadings() {
    return readings;
  }

  public void setReadings(List<Reading> readings) {
    this.readings = readings;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }
}
