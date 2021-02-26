package pl.warehouseapi.application.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinQuantityValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinQuantity {
    String message() default "The input list cannot contain quantity lower than 1";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
