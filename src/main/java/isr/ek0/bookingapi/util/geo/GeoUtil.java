package isr.ek0.bookingapi.util.geo;

import java.util.List;

import static com.google.common.primitives.Doubles.tryParse;
import static isr.ek0.bookingapi.util.exception.ExceptionUtil.validateCoordinates;
import static java.util.stream.Collectors.toList;

public class GeoUtil {
    public static List<Double> parseCoordinates(List<String> coordinates) {
        return validateCoordinates(coordinates.stream().map(coord -> coord == null ? null : tryParse(coord)).collect(toList()));
    }

    public static Double parseDistance(String distanceStr) {
        if (distanceStr == null) {
            return Double.MAX_VALUE;
        }
        Double distance = tryParse(distanceStr);
        return (distance == null || distance < 0.0) ? Double.MAX_VALUE : distance;
    }
}
