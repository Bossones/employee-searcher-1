package ru.croc.employee_searcher.validator;

import static java.util.Objects.isNull;

import ru.croc.employee_searcher.dto.Employee;

import java.util.Collections;
import java.util.List;

public class EmployeeValidator implements Validator<Employee> {

    private final List<Validator<Employee>> fieldValidators;

    public EmployeeValidator(List<Validator<Employee>> fieldValidators) {
        if (isNull(fieldValidators)) {
            fieldValidators = Collections.emptyList();
        }
        this.fieldValidators = fieldValidators;
    }

    @Override
    public boolean validate(Employee data) {
        boolean valid = true;
        for (Validator<Employee> fieldValidator : fieldValidators) {
            valid &= fieldValidator.validate(data);
        }
        return valid;
    }
}
