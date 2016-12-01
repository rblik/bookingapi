package isr.ek0.bookingapi.repo.restaurant;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.RestaurantRepository;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.geoNear;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Autowired
    private MongoTemplate template;

    @Override
    public Restaurant save(String loggedUserEmail, Restaurant restaurant) {
        restaurant.setOwnerEmail(loggedUserEmail);
        return crudRepository.insert(restaurant);
    }

    @Override
    public Restaurant get(String name) {
        return name == null ? null : crudRepository.findOne(name);
    }

    @Override
    public Restaurant delete(String loggedUserEmail, String restaurantName) {
        return template.findAndRemove(new Query(where("ownerEmail").is(loggedUserEmail)
                .andOperator(where("_id").is(restaurantName))), Restaurant.class);
    }

    @Override
    public List<Restaurant> getAll(String sortStr) {
        Sort sort = "desc".equalsIgnoreCase(sortStr) ? new Sort(DESC, "_id") : new Sort(ASC, "_id");
        return crudRepository.findAll(sort);
    }

    @Override
    public List<RestaurantWithDistance> getAllByLocationAndDistance(List<Double> coordinates, Double maxDistance) {
        NearQuery query = NearQuery
                .near(coordinates.get(0), coordinates.get(1))
                .inKilometers()
                .maxDistance(maxDistance)
                .spherical(true);
        Aggregation a = newAggregation(geoNear(query, "distance"));
        return template.aggregate(a, Restaurant.class, RestaurantWithDistance.class).getMappedResults();
//        return template.find(new Query(where("location").nearSphere(new Point(coordinates.get(0), coordinates.get(1)))), Restaurant.class);
    }

    @Override
    public List<Restaurant> getAllByOwnerEmail(String ownerEmail) {
        return crudRepository.findByOwnerEmail(ownerEmail);
    }

    @Override
    public Restaurant saveMeal(String loggedUserEmail, String restaurantName, Meal meal) {
        Restaurant restaurant = getRestaurantForUpdate(loggedUserEmail, restaurantName);
        return restaurant == null ? null : crudRepository.save(restaurant.addMeals(of(meal)));
    }

    @Override
    public Restaurant saveMeals(String loggedUserEmail, String restaurantName, List<Meal> meals) {
        Restaurant restaurant = getRestaurantForUpdate(loggedUserEmail, restaurantName);
        return restaurant == null ? null : crudRepository.save(restaurant.addMeals(meals));
    }

    private Restaurant getRestaurantForUpdate(String loggedUserEmail, String restaurantName) {
        return template.findOne(new Query(where("ownerEmail").is(loggedUserEmail)
                .andOperator(where("_id").is(restaurantName))), Restaurant.class);
    }

    @Override
    public int deleteAllMeals(String loggedUserEmail, String restaurantName) {
        return template.updateMulti(new Query(where("ownerEmail").is(loggedUserEmail)
                        .andOperator(where("_id").is(restaurantName))),
                new Update().set("menu", emptyList()), Restaurant.class).getN();
    }
}
