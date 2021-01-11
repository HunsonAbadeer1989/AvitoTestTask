package main.service.impl;

import main.api.request.AddRoomRequest;
import main.api.response.AddRoomResponse;
import main.api.response.ListOfRoomsResponse;
import main.api.response.ResponseApi;
import main.model.HotelRoom;
import main.repository.BookingRepository;
import main.repository.RoomRepository;
import main.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseEntity<ResponseApi> addRoom(AddRoomRequest addRoomRequest) {
        HotelRoom newRoom = roomRepository.save(new HotelRoom(addRoomRequest.getDescription(),
                addRoomRequest.getPrice()));
        return new ResponseEntity<>(new AddRoomResponse(newRoom.getId()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseApi> deleteRoom(long id) {
        HotelRoom tempRoom = roomRepository.findById(id);
        if (tempRoom == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            tempRoom.setReservations(new ArrayList<>());
            roomRepository.delete(tempRoom);
            bookingRepository.deleteBookingsByRoomId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> getListOfRooms(String sort) {
        if(sort == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        ResponseApi response = new ListOfRoomsResponse(roomList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
