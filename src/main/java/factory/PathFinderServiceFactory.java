package factory;

import java.util.HashMap;
import java.util.Map;

import repository.StationRepository;
import service.PathFinderService;
import service.impl.BestByPriceService;
import service.impl.BestByTimeService;

public class PathFinderServiceFactory {

    private Map<String, PathFinderService> services;
    private StationRepository repository;

    public PathFinderServiceFactory(StationRepository repository) {
        services = new HashMap<>();
        this.repository = repository;
    }

    public PathFinderService getService(String number) {
        updateServicesMap();
        return services.getOrDefault(number, null);
    }

    public Map<String, PathFinderService> getServices() {
        updateServicesMap();
        return services;
    }

    private void updateServicesMap() {
        services.put("1", new BestByPriceService(repository));
        services.put("2", new BestByTimeService(repository));
    }
}
