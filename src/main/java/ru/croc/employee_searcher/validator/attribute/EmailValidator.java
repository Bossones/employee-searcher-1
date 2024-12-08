package ru.croc.employee_searcher.validator.attribute;

import static ru.croc.employee_searcher.utils.StringUtils.isBlank;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator<Employee> {

    private static final Pattern pattern = Pattern.compile("^[A-я.]+@[A-я]+\\.[a-z]{2,}$");

    @Override
    public boolean validate(Employee employee) {
        final String email = employee.email();
        if (isBlank(email)) {
            return false;
        }
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
