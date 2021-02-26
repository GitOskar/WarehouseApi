package pl.warehouseapi.application.validation;

import pl.warehouseapi.application.dto.ProductItemList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.List;

public class MinQuantityValidator implements ConstraintValidator<MinQuantity, List<ProductItemList>> {
    @Override
    public boolean isValid(List<ProductItemList> value, ConstraintValidatorContext context) {

        for (var item : value)
            if (item.quantity.compareTo(BigDecimal.ONE) < 0)
                return false;

        return true;
    }
}
