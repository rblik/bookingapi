package isr.ek0.bookingapi.repo.booking;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.BookingId;
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
        booking.getBookingId().setUserEmail(loggedUserEmail);
//        upsert
        return crudRepository.save(booking);
    }

    @Override
    public List<Booking> getAll(String loggedUserEmail) {
        return crudRepository.findAllByUserEmail(loggedUserEmail);
    }

    @Override
    public List<Booking> getAllByRestaurantName(String restaurantName, LocalDate today) {
        return template.find(new Query(where("restaurantName").is(restaurantName)
                .andOperator(where("_id.date").is(today))), Booking.class);
    }

    @Override
    public void deleteAllByRestaurant(String restaurantName) {
        template.findAllAndRemove(new Query(Criteria.where("restaurantName").is(restaurantName)), Booking.class);
    }

    @Override
    public void delete(String loggedUserEmail, LocalDate date) {
        template.findAndRemove(new Query(where("_id").is(new BookingId(loggedUserEmail, date))), Booking.class);
    }

    @Override
    public void deleteAll(String loggedUserEmail) {
        template.findAllAndRemove(new Query(where("_id.userEmail").is(loggedUserEmail)), Booking.class);
    }
}
