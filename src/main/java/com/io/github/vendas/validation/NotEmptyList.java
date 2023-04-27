package com.io.github.vendas.validation;

import com.io.github.vendas.validation.constraintvalidation.NotemptyListValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotemptyListValidator.class)
public @interface NotEmptyList {

    String message() default "A Lista não pode ser vazia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
