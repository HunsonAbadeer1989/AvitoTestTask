package main.service.impl;

import main.api.response.AddRoomResponse;
import main.api.response.BadRequestResponse;
import main.api.response.ListOfRoomsResponse;
import main.api.response.ResponseApi;
import main.model.HotelRoom;
import main.repository.RoomRepository;
import main.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<ResponseApi> addRoom(String description, Double price) {
        HotelRoom newRoom = roomRepository.save(new HotelRoom(description, price));
        return new ResponseEntity<>(new AddRoomResponse(newRoom.getId()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseApi> deleteRoom(long id) {
        Optional<HotelRoom> tempRoom = roomRepository.findById(id);
        if (tempRoom.isEmpty()) {
            return new ResponseEntity<>(new BadRequestResponse("This room Id " + id + " isn't exist"),
                    HttpStatus.BAD_REQUEST);
        } else {
            roomRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> getListOfRooms(String sort) {
        if (sort == null) {
            return new ResponseEntity<>(new BadRequestResponse("Wrong sort param!"),
                    HttpStatus.BAD_REQUEST);
        }
        List<HotelRoom> roomList;
        switch (sort) {
            case ("date_asc"):
                roomList = roomRepository.roomsByDateASC();
                break;
            case ("date_desc"):
                roomList = roomRepository.roomsByDateDESC();
                break;
            default:
                roomList = roomRepository.roomsByPrice();
                break;
        }
        if(roomList.isEmpty()){
            return new ResponseEntity<>(new BadRequestResponse("List is empty!"),
                    HttpStatus.BAD_REQUEST);
        }
        ResponseApi response = new ListOfRoomsResponse(roomList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
