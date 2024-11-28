package com.codeit.sprint.team3.backend.common.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public abstract class SelfValidating <T> {
    private final Validator validator;

    public SelfValidating() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected void validateSelf() {
        @SuppressWarnings("unchecked")
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
