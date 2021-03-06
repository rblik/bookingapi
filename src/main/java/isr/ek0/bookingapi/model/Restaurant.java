package isr.ek0.bookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

@Document(collection = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
public class Restaurant extends ResourceSupport implements Serializable{

    @Id
    @NotEmpty
    private String name;
    @NotNull
    @GeoSpatialIndexed(type = GEO_2DSPHERE)
    private GeoJsonPoint location;
    @Valid
    private List<Meal> menu = new ArrayList<>();
    @Indexed
    private String ownerEmail;
    @NotNull
    private LocalTime openTime;
    @NotNull
    private LocalTime closeTime;

    public Restaurant(String name, GeoJsonPoint location, List<Meal> menu, LocalTime openTime, LocalTime closeTime) {
        this.name = name;
        this.location = location;
        this.menu = menu;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Restaurant addMeal(Meal newMeal) {
        this.getMenu().add(newMeal);
        return this;
    }

    public Restaurant deleteMeal(String mealDescription) {
        this.setMenu(getMenu().stream().filter(meal -> !meal.getDescription().equalsIgnoreCase(mealDescription)).collect(toList()));
        return this;
    }
}
