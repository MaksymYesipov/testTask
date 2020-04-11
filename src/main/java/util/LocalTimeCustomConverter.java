package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.opencsv.bean.AbstractBeanField;

public class LocalTimeCustomConverter extends AbstractBeanField {

    private static final Logger LOGGER = Logger.getLogger(LocalTimeCustomConverter.class);

    @Override
    protected Object convert(String value) {
        if (value.length() > 8) {
            value = value.substring(0, 8);
        }
        return LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
