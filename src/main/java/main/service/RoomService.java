package main.service;

import main.api.request.AddRoomRequest;
import main.api.response.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<ResponseApi> addRoom(AddRoomRequest addRoomRequest);

    ResponseEntity<ResponseApi> deleteRoom(long id);

    ResponseEntity<ResponseApi> getListOfRooms(String sort);
}
