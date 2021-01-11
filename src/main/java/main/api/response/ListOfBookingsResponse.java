package main.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ListOfBookingsResponse implements ResponseApi{

    private List<BookingListResponse> listOfBookings;

    public ListOfBookingsResponse(List<Booking> list) {
        listOfBookings = new ArrayList<>();
        for(Booking booking : list){
            listOfBookings.add(new BookingListResponse(booking));
        }
    }

    @Data
    static class BookingListResponse{
        private long id;
        private LocalDate date_start;
        private LocalDate date_end;

        public BookingListResponse(Booking booking) {
            this.id = booking.getId();
            this.date_start = booking.getDate_start();
            this.date_end = booking.getDate_end();
        }
    }
}
