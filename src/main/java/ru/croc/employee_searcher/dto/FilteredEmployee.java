package ru.croc.employee_searcher.dto;

import ru.croc.employee_searcher.utils.StringUtils;

import java.text.MessageFormat;
import java.util.UUID;

public record FilteredEmployee(UUID Id, String name, String surname, String email, int age, String department, String fullName) {

    public FilteredEmployee {
        name = StringUtils.capitalizeFirstLetter(name);
        surname = StringUtils.capitalizeFirstLetter(surname);
        fullName = name + " " + surname;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
            "{0},{1},{2},{3},{4},{5},{6}",
            this.Id,
            this.name,
            this.surname,
            this.email,
            this.age,
            this.department,
            this.fullName
        );
    }
}
