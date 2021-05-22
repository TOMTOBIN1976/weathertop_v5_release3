package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;
import models.Member;
import models.Station;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> station = member.station;
    render("dashboard.html", station);
  }

  public static void addStation(String title, double lat, double lng) {
    Logger.info("Adding a Playlist");
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(title, lat, lng);
    member.station.add(station);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Logger.info("Deleting a Playlist");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.station.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }
}




