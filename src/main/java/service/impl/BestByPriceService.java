package service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import entity.Path;
import entity.Route;
import entity.Station;
import repository.StationRepository;

public class BestByPriceService extends AbstractPathFinderService {

    public BestByPriceService(StationRepository repository) {
        super(repository);
    }

    @Override
    protected List<Route> getBestAvailableRoutes(Station station, Path path) {
        float minPrice = station.getOutgoingRoutes().stream()
                .filter(r -> !isAlreadyVisited(r.getDestination(), path))
                .map(Route::getPrice)
                .min(Comparator.naturalOrder()).orElse(Float.MAX_VALUE);
        return station.getOutgoingRoutes().stream().filter(r -> r.getPrice() == minPrice).collect(Collectors.toList());
    }

    @Override
    protected List<Path> postProcessResults(List<Path> paths) {
        List<Path> result = validateFullPaths(paths);
        float minPrice = result.stream().map(Path::getFullPrice).min(Comparator.naturalOrder()).orElse(Float.MAX_VALUE);
        return result.stream().filter(path -> path.getFullPrice() == minPrice).collect(Collectors.toList());
    }
}
