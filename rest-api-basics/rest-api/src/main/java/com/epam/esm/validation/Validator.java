package com.epam.esm.validation;

public interface Validator<T> {

    ValidationResult validate(T entity);

}
