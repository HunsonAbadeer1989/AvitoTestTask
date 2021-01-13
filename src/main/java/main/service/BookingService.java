package main.service;

import main.api.response.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<ResponseApi> deleteById(long id);

    ResponseEntity<ResponseApi> addBooking(Long room_id, String date_start, String date_end);

    ResponseEntity<ResponseApi> getListOfBookings(long id);

}