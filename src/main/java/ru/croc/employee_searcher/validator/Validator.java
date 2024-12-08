package ru.croc.employee_searcher.validator;

public interface Validator<T> {

    boolean validate(T data);
}
