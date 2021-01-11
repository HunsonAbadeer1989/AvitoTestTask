package main.service.impl;

import main.api.request.AddBookingRequest;
import main.api.response.AddBookingResponse;
import main.api.response.ListOfBookingsResponse;
import main.api.response.ResponseApi;
import main.model.Booking;
import main.model.HotelRoom;
import main.repository.BookingRepository;
import main.repository.RoomRepository;
import main.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<ResponseApi> addBooking(AddBookingRequest request) {
        HotelRoom hotelRoom = roomRepository.findById(request.getRoom_id());
        if(hotelRoom == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Booking booking = bookingRepository.save(new Booking(hotelRoom,
                    request.getDate_start(),
                    request.getDate_end()));
            return new ResponseEntity<>(new AddBookingResponse(booking.getId()), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> deleteById(long id) {
        Booking tempBooking = bookingRepository.findById(id);
        if (tempBooking == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            bookingRepository.delete(tempBooking);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> getListOfBookings(long id) {
        HotelRoom hotelRoom = roomRepository.findById(id);
        if(hotelRoom == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            List<Booking> listOfBookings = hotelRoom.getReservations()
                                                            .stream()
                                                            .sorted(Comparator.comparing(Booking::getDate_start))
                                                            .collect(Collectors.toList());
            ResponseApi response = new ListOfBookingsResponse(listOfBookings);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
    }
}
