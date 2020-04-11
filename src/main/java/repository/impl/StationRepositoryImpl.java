package repository.impl;

import java.util.ArrayList;
import java.util.List;

import entity.Station;
import repository.StationRepository;

public class StationRepositoryImpl implements StationRepository {

    private List<Station> stations;

    public StationRepositoryImpl() {
        stations = new ArrayList<>();
    }

    @Override
    public List<Station> getStations() {
        return stations;
    }

    @Override
    public void add(Station station) {
        stations.add(station);
    }

    @Override
    public Station getStationByNumber(String number) {
        return stations.stream().filter(s -> s.getNumber().equals(number)).findFirst().orElse(null);
    }

    @Override
    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
