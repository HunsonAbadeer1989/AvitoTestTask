package main.api.response;

import lombok.Data;

@Data
public class BadRequestResponse implements ResponseApi {

    private String message;

    public BadRequestResponse(String message) {
        this.message = message;
    }
}
