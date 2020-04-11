package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import consts.TaskConstants;

public class CommonUtils {

    public static long calculateMinutes(LocalTime from, LocalTime to) {
        long result = from.until(to, ChronoUnit.MINUTES);
        return to.isAfter(from) ? result : result + TaskConstants.ONE_DAY_IN_MINUTES;
    }

    public static String formatMinutesOutput(long minutes) {
        return String.format("%s hours %s minutes", (int) (minutes / TaskConstants.MINUTES_IN_HOUR), minutes % TaskConstants.MINUTES_IN_HOUR);
    }

    public static double roundFloat(float value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(TaskConstants.FLOAT_PLACES_TO_ROUND, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private CommonUtils() {
        throw new UnsupportedOperationException();
    }
}
