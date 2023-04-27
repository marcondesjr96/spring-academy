package com.io.github.vendas.validation.constraintvalidation;

import com.io.github.vendas.validation.NotEmptyList;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

public class NotemptyListValidator implements ConstraintValidator<NotEmptyList, List> {


    @Override
    public void initialize(NotEmptyList constraintAnnotation) {


    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
