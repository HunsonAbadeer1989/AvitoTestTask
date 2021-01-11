package main.controller;

import com.sun.istack.NotNull;
import main.api.request.AddBookingRequest;
import main.api.response.ResponseApi;
import main.service.impl.BookingServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingServiceImpl bookingService;

    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> addBooking(@RequestBody AddBookingRequest request){
        return bookingService.addBooking(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteBooking(@PathVariable("id") long id){
        return bookingService.deleteById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseApi> getListOfBookings(@RequestParam @NotNull long room_id){
        return bookingService.getListOfBookings(room_id);
    }
}
