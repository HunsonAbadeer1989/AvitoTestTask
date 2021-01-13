package main.service;

import main.api.response.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<ResponseApi> addRoom(String description, Double price);

    ResponseEntity<ResponseApi> deleteRoom(long id);

    ResponseEntity<ResponseApi> getListOfRooms(String sort);

}
