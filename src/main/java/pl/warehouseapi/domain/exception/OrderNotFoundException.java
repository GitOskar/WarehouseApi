package pl.warehouseapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends NullPointerException {
    public OrderNotFoundException(int id) {
        super("DataBase does not contain order with id equals " + id);
    }
}
