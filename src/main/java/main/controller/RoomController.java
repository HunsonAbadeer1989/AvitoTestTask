package main.controller;

import com.sun.istack.NotNull;
import main.api.response.ResponseApi;
import main.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> addRoom(@RequestParam @NotNull String description,
                                               @RequestParam @NotNull Double price) {
        return roomService.addRoom(description, price);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseApi> getListRooms(@RequestParam(defaultValue = "price") String sort) {
        return roomService.getListOfRooms(sort);
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<ResponseApi> deleteRoom(@PathVariable("id") long id) {
        return roomService.deleteRoom(id);
    }

}
