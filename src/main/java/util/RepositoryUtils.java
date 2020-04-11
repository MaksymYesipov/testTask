package util;

import java.util.List;

import org.apache.log4j.Logger;

import bean.Flight;
import entity.Route;
import entity.Station;
import repository.StationRepository;

public class RepositoryUtils {

    private static final Logger LOGGER = Logger.getLogger(RepositoryUtils.class);

    public static void fillStationRepository(List<Flight> flights, StationRepository repository) {
        LOGGER.info("Station repository initialization started");

        flights.stream().map(Flight::getStart).distinct().forEach(number -> repository.add(new Station(number)));
        flights.forEach(flight -> repository.getStationByNumber(flight.getStart()).addRoute(new Route(
                flight.getTrainNumber(),
                repository.getStationByNumber(flight.getFinish()),
                flight.getPrice(),
                CommonUtils.calculateMinutes(flight.getDeparture(), flight.getArrival()))));

        LOGGER.info("Station repository initialized");
    }

    private RepositoryUtils() {
        throw new UnsupportedOperationException();
    }
}
