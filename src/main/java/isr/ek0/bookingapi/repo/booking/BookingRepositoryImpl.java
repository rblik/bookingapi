package isr.ek0.bookingapi.repo.booking;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    MongoTemplate template;

    @Autowired
    private CrudBookingRepository crudRepository;

    @Override
    public Booking save(String loggedUserEmail, Booking booking) {
        Restaurant restaurant = template.findOne(new Query(where("_id").is(booking.getRestaurantName())), Restaurant.class);
        booking.getBookingId().setUserEmail(loggedUserEmail);
//        upsert
        return restaurant == null ? null : crudRepository.save(booking);
    }

    @Override
    public List<Booking> getAll(String loggedUserEmail) {
        return crudRepository.findAllByUserEmail(loggedUserEmail);
    }

    @Override
    public List<Booking> getAllByRestaurantName(String loggedUserEmail, String restaurantName, LocalDate today) {
        Restaurant restaurant = template.findOne(new Query(where("ownerEmail").is(loggedUserEmail)
                .andOperator(where("_id").is(restaurantName))), Restaurant.class);
        return restaurant == null ? null : template.find(new Query(where("restaurantName").is(restaurantName)
                .andOperator(where("_id.date").is(today))), Booking.class);
    }

    @Override
    public List<Booking> deleteAllByRestaurant(String restaurantName) {
        return template.findAllAndRemove(new Query(Criteria.where("restaurantName").is(restaurantName)), Booking.class);
    }
}
