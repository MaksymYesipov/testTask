package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Station {

    private String number;
    private List<Route> outgoingRoutes;

    public Station(String number) {
        this.number = number;
        outgoingRoutes = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Route> getOutgoingRoutes() {
        return outgoingRoutes;
    }

    public void setOutgoingRoutes(List<Route> outgoingRoutes) {
        this.outgoingRoutes = outgoingRoutes;
    }

    public void addRoute(Route route) {
        if (!this.equals(route.getDestination())) {
            outgoingRoutes.add(route);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;

        Station station = (Station) o;

        if (!Objects.equals(number, station.number)) return false;
        return Objects.equals(outgoingRoutes, station.outgoingRoutes);
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (outgoingRoutes != null ? outgoingRoutes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Station{" +
                "number: " + number +
                " Routes: " + System.lineSeparator() + outgoingRoutes;
    }
}
