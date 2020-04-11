package bean;

import java.time.LocalTime;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

import util.LocalTimeCustomConverter;

public class Flight {

    @CsvBindByPosition(position = 0)
    private String trainNumber;
    @CsvBindByPosition(position = 1)
    private String start;
    @CsvBindByPosition(position = 2)
    private String finish;
    @CsvBindByPosition(position = 3)
    private float price;
    @CsvCustomBindByPosition(position = 4, converter = LocalTimeCustomConverter.class)
    private LocalTime departure;
    @CsvCustomBindByPosition(position = 5, converter = LocalTimeCustomConverter.class)
    private LocalTime arrival;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "trainNumber=" + trainNumber +
                ", start=" + start +
                ", finish=" + finish +
                ", price=" + price +
                ", departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
