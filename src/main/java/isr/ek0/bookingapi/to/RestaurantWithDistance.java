package isr.ek0.bookingapi.to;

import isr.ek0.bookingapi.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

public class RestaurantWithDistance extends Restaurant {

    @Getter
    @Setter
    private String distance;

    @Override
    public String toString() {
        return "RestaurantWithDistance{" +
                "distance='" + distance + '\'' +
                "} " + super.toString();
    }
}
