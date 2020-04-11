package repository;

import java.util.List;

import entity.Station;

public interface StationRepository {

    void add(Station station);

    Station getStationByNumber(String number);

    void setStations(List<Station> stations);

    List<Station> getStations();
}
