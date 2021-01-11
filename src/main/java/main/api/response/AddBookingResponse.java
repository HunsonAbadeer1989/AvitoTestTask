package main.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookingResponse implements ResponseApi{
    private long booking_id;
}
