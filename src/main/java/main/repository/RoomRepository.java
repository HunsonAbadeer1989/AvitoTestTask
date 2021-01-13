package main.repository;

import main.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<HotelRoom, Long> {

//    Optional<HotelRoom> findById(long id);

    @Query(value = "SELECT id, description, price, date_of_creation FROM room AS r " +
            "ORDER BY r.date_of_creation ASC", nativeQuery = true)
    List<HotelRoom> roomsByDateASC();

    @Query(value = "SELECT id, description, price, date_of_creation FROM room AS r " +
            "ORDER BY r.date_of_creation DESC", nativeQuery = true)
    List<HotelRoom> roomsByDateDESC();

    @Query(value = "SELECT id, description, price, date_of_creation FROM room AS r " +
            "ORDER BY r.price ASC", nativeQuery = true)
    List<HotelRoom> roomsByPrice();
}
