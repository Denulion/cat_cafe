package lt.techin.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CapitalizationValidator.class)
public @interface Capitalized {

    String message() default "Cat name should start with capital letter!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
