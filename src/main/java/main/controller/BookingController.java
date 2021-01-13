package main.controller;

import com.sun.istack.NotNull;
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
    public ResponseEntity<ResponseApi> addBooking(@RequestParam @NotNull Long room_id,
                                                  @RequestParam @NotNull String date_start,
                                                  @RequestParam @NotNull String date_end){
        return bookingService.addBooking(room_id, date_start, date_end);
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
