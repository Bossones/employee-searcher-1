package ru.croc.employee_searcher.validator.attribute;

import static java.util.Objects.isNull;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.validator.Validator;

import java.util.UUID;

public class UuidValidator implements Validator<Employee> {

    @Override
    public boolean validate(Employee data) {
        final UUID guid = data.Id();
        if (isNull(guid)) {
            return false;
        }
        return true;
    }
}
