package ru.croc.employee_searcher.validator.attribute;

import static ru.croc.employee_searcher.utils.StringUtils.isBlank;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.validator.Validator;

public class NameValidator implements Validator<Employee> {

    @Override
    public boolean validate(Employee data) {
        final String name = data.name();
        if (isBlank(name)) {
            return false;
        }
        return true;
    }
}
