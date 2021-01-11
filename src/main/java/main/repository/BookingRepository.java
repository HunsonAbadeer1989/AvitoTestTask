package main.repository;

import main.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    Booking findById(long id);

    @Query(value = "DELETE FROM booking WHERE room_id=?1", nativeQuery = true)
    void deleteBookingsByRoomId(long room_id);

}
