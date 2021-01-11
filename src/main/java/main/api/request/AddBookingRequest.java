package main.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AddBookingRequest implements RequestApi {
    private long room_id;
    private LocalDate date_start;
    private LocalDate date_end;

}
