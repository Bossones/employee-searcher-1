package ru.croc.employee_searcher.validator.attribute;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.validator.Validator;

public class AgeValidator implements Validator<Employee> {

    @Override
    public boolean validate(Employee data) {
        final int age = data.age();
        return age >= 18 && age <= 150;
    }
}
