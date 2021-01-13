package main.service.impl;

import main.api.response.AddBookingResponse;
import main.api.response.BadRequestResponse;
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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    @Transactional
    public ResponseEntity<ResponseApi> addBooking(Long room_id, String date_start, String date_end) {
        HotelRoom room = roomRepository.findById(room_id).orElse(null);
        if (room == null) {
            return new ResponseEntity<>(new BadRequestResponse("Wrong booking request "
                    + room_id +
                    " ,check params!"),
                    HttpStatus.BAD_REQUEST);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(date_start, formatter);
            LocalDate end = LocalDate.parse(date_end, formatter);
            if (isValid(date_start) && isValid(date_end)) {
                Booking booking = bookingRepository.save(new Booking(room, start, end));
                return new ResponseEntity<>(new AddBookingResponse(booking.getId()), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new BadRequestResponse("Date is not valid!"), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<ResponseApi> deleteById(long id) {
        Booking tempBooking = bookingRepository.findById(id);
        if (tempBooking == null) {
            return new ResponseEntity<>(new BadRequestResponse("Have no booking with this Id " + id),
                    HttpStatus.BAD_REQUEST);
        } else {
            bookingRepository.delete(tempBooking);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> getListOfBookings(long id) {
        Optional<HotelRoom> hotelRoom = roomRepository.findById(id);
        if (hotelRoom.isEmpty()) {
            return new ResponseEntity<>(new BadRequestResponse("Have no room with this Id " + id),
                    HttpStatus.BAD_REQUEST);
        } else {
            List<Booking> listOfBookings = hotelRoom.get().getReservations()
                    .stream()
                    .sorted(Comparator.comparing(Booking::getDate_start))
                    .collect(Collectors.toList());
            ResponseApi response = new ListOfBookingsResponse(listOfBookings);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public boolean isValid(String input) {
        DateTimeFormatter formatter =
                new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                        .parseStrict().toFormatter();
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            e.getMessage();
        }
        return false;
    }
}
