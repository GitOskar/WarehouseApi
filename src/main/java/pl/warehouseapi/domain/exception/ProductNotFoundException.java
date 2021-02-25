package pl.warehouseapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotFoundException extends NullPointerException {
    public ProductNotFoundException(int id) {
        super("DataBase does not contain product with id equals " + id);
    }
}
