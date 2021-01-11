package main.api.request;

import lombok.Data;

@Data
public class AddRoomRequest implements RequestApi {
    private String description;
    private Double price;

    public AddRoomRequest(String description, Double price) {
        this.description = description;
        this.price = price;
    }
}
