package main.controller;

import main.api.request.AddRoomRequest;
import main.api.response.ResponseApi;
import main.model.HotelRoom;
import main.repository.RoomRepository;
import main.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> addRoom(@RequestBody AddRoomRequest addRoomRequest){
        return roomService.addRoom(addRoomRequest);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseApi> getListRooms(@RequestParam(defaultValue = "price") String sort){
        return roomService.getListOfRooms(sort);
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<ResponseApi> deleteRoom(@PathVariable("id") long id){
        return roomService.deleteRoom(id);
    }

}
