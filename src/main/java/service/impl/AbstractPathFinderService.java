package service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import entity.Path;
import entity.Route;
import entity.Station;
import repository.StationRepository;
import service.PathFinderService;

public abstract class AbstractPathFinderService implements PathFinderService {

    protected StationRepository repository;

    AbstractPathFinderService(StationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Path> find() {
        List<Path> resultPaths = initResultList();
        repository.getStations().forEach(s -> findBestWayForStation(s, resultPaths));
        return postProcessResults(resultPaths);
    }

    private List<Path> initResultList() {
        List<Path> paths = new ArrayList<>();
        repository.getStations().forEach(station -> paths.add(new Path(station)));
        return paths;
    }

    private void findBestWayForStation(Station station, List<Path> paths) {
        List<Path> eligiblePaths = paths.stream()
                .filter(p -> isEligiblePathsByLastStation(p, station))
                .collect(Collectors.toList());
        if (eligiblePaths.isEmpty()) {
            return;
        }
        for (Path p : eligiblePaths) {
            paths.addAll(splitPathForStation(p, station));
            paths.remove(p);
        }
        List<Station> stations = paths.stream().map(Path::getLastStation).collect(Collectors.toList());
        for (Station s : stations) {
            findBestWayForStation(s, paths);
        }
    }

    private List<Path> splitPathForStation(Path path, Station station) {
        List<Route> bestRoutes = getBestAvailableRoutes(station, path);
        if (bestRoutes.isEmpty()) {
            return Collections.singletonList(new Path(path));
        }
        List<Path> splittedPaths = new ArrayList<>();
        for (Route route : bestRoutes) {
            Path newPath = new Path(path);
            newPath.addRoute(route);
            splittedPaths.add(newPath);
        }
        return splittedPaths;
    }

    private boolean isEligiblePathsByLastStation(Path path, Station station) {
        return path.getLastStation().equals(station) &&
                !station.getOutgoingRoutes().stream().map(Route::getDestination).allMatch(s -> isAlreadyVisited(s, path));
    }


    boolean isAlreadyVisited(Station station, Path path) {
        return path.getStartStation().equals(station) ||
                path.getRoutes().stream().anyMatch(route -> route.getDestination().equals(station));
    }

    List<Path> validateFullPaths(List<Path> paths) {
        return paths.stream()
                .filter(path -> Objects.nonNull(path.getStartStation())
                        && path.getRoutes().size() == repository.getStations().size() - 1)
                .collect(Collectors.toList());
    }

    protected abstract List<Route> getBestAvailableRoutes(Station station, Path path);

    protected abstract List<Path> postProcessResults(List<Path> result);
}
