package isr.ek0.bookingapi.repo.booking;

import isr.ek0.bookingapi.model.Booking;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CrudBookingRepository extends MongoRepository<Booking, ObjectId> {

    @Query("{'_id.userEmail':'?0'}")
    List<Booking> findAllByUserEmail(String userEmail);
}
