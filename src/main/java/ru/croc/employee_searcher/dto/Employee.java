package ru.croc.employee_searcher.dto;

import java.text.MessageFormat;
import java.util.UUID;

public record Employee(UUID Id, String name, String surname, String email, int age, String department) {

    @Override
    public String toString() {
        return MessageFormat.format(
            "{0},{1},{2},{3},{4},{5}",
            this.Id,
            this.name,
            this.surname,
            this.email,
            this.age,
            this.department
        );
    }
}
