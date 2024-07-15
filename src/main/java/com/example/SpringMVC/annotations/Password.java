package com.example.SpringMVC.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "Password format is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
