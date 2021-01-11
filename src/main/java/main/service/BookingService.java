package main.service;

import main.api.request.AddBookingRequest;
import main.api.response.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface BookingService {

     ResponseEntity<ResponseApi> deleteById(long id);

     ResponseEntity<ResponseApi> addBooking(AddBookingRequest request);

     ResponseEntity<ResponseApi> getListOfBookings(long id);

}
