package service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entity.Path;
import entity.Route;
import entity.Station;
import repository.StationRepository;

public class BestByTimeService extends AbstractPathFinderService {

    public BestByTimeService(StationRepository repository) {
        super(repository);
    }

    @Override
    protected List<Route> getBestAvailableRoutes(Station station, Path path) {
        float minTime = station.getOutgoingRoutes().stream()
                .filter(r -> !isAlreadyVisited(r.getDestination(), path))
                .map(Route::getTime)
                .min(Comparator.naturalOrder()).orElse(Long.MAX_VALUE);
        return station.getOutgoingRoutes().stream().filter(r -> r.getTime() == minTime).collect(Collectors.toList());
    }

    @Override
    protected List<Path> postProcessResults(List<Path> paths) {
        List<Path> result = validateFullPaths(paths);
        float minTime = result.stream().map(Path::getFullTime).min(Comparator.naturalOrder()).orElse(Long.MAX_VALUE);
        return result.stream().filter(path -> path.getFullTime() == minTime).collect(Collectors.toList());
    }
}
