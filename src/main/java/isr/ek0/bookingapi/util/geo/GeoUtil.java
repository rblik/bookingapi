package isr.ek0.bookingapi.util.geo;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.google.common.primitives.Doubles.tryParse;
import static isr.ek0.bookingapi.util.exception.ExceptionUtil.parseAndValidateCoordinates;

public class GeoUtil {
    public static List<Double> parseCoordinates(String longitude, String latitude) {
        return parseAndValidateCoordinates(of(longitude, latitude));
    }

    public static Double parseDistance(String distanceStr) {
        if (distanceStr == null) {
            return Double.MAX_VALUE;
        }
        Double distance = tryParse(distanceStr);
        return (distance == null || distance < 0.0) ? Double.MAX_VALUE : distance;
    }
}
