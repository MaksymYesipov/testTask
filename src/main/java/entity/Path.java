package entity;

import java.util.LinkedList;
import java.util.List;

import util.CommonUtils;

public class Path {

    private Station startStation;
    private List<Route> routes;
    private float fullPrice;
    private long fullTime;

    public Path(Station startStation) {
        this.startStation = startStation;
        routes = new LinkedList<>();
        fullPrice = 0f;
        fullTime = 0;
    }

    public Path(Path path) {
        this.startStation = path.startStation;
        routes = new LinkedList<>(path.routes);
        fullPrice = path.fullPrice;
        fullTime = path.fullTime;
    }

    public void addRoute(Route route) {
        routes.add(route);
        fullPrice += route.getPrice();
        fullTime += route.getTime();
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public float getFullPrice() {
        return fullPrice;
    }

    public long getFullTime() {
        return fullTime;
    }

    public Station getLastStation() {
        return routes.isEmpty() ? startStation : routes.get(routes.size() - 1).getDestination();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        routes.forEach(r -> sb.append(System.lineSeparator())
                .append("\t -> flight to #").append(r.getDestination().getNumber())
                .append(" by train #").append(r.getTrain())
                .append(" (p: ").append(r.getPrice())
                .append(", t: ").append(CommonUtils.formatMinutesOutput(r.getTime())).append(")"));
        return "Start from " + startStation.getNumber() + sb.toString() +
                System.lineSeparator() +
                " Price: " + CommonUtils.roundFloat(fullPrice) +
                ", time: " + CommonUtils.formatMinutesOutput(fullTime);
    }
}
