package isr.ek0.bookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

@Document(collection = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    private String name;
    @GeoSpatialIndexed(type = GEO_2DSPHERE)
    private GeoJsonPoint location;
    private List<Meal> menu;
    @Indexed
    private String ownerEmail;

//    @DBRef(lazy = true)
//    @JsonIgnore
//    private User owner;

    public Restaurant(String name, GeoJsonPoint location, List<Meal> menu) {
        this.name = name;
        this.location = location;
        this.menu = menu;
    }
}
