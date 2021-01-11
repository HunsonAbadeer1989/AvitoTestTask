package main.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import main.model.HotelRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ListOfRoomsResponse implements ResponseApi {

    List<RoomForListResponse> hotelRooms;

    public ListOfRoomsResponse(List<HotelRoom> hotelRoomsList) {
        hotelRooms = new ArrayList<>();
        for(HotelRoom room : hotelRoomsList){
            hotelRooms.add(new RoomForListResponse(room));
        }
    }

    @Data
    static class RoomForListResponse {
        private Long id;
        private String description;
        private Double price;
        private LocalDate date_of_creations;

        public RoomForListResponse(HotelRoom hotelRoom){
            id = hotelRoom.getId();
            description = hotelRoom.getDescription();
            price = hotelRoom.getPrice();
            date_of_creations = hotelRoom.getDate();
        }
    }

}
