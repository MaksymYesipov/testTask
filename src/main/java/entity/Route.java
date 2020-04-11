package entity;

import util.CommonUtils;

public class Route {

    private String train;
    private Station destination;
    private float price;
    private long time;

    public Route(String train, Station destination, float price, long time) {
        this.train = train;
        this.destination = destination;
        this.price = price;
        this.time = time;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "\tRoute to " + destination.getNumber() + " station " +
                "by train #" + train +
                ", price: " + price +
                ", time: " + CommonUtils.formatMinutesOutput(time) + System.lineSeparator();
    }
}
