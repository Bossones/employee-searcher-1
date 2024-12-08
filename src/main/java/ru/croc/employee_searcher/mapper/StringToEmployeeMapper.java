package ru.croc.employee_searcher.mapper;

import ru.croc.employee_searcher.dto.Employee;

import java.util.UUID;

public class StringToEmployeeMapper implements Mapper<String, Employee> {

    @Override
    public Employee map(String input) {
        final String[] attributes = input.split(",");

        return new Employee(
            UUID.fromString(attributes[0]),
            attributes[1],
            attributes[2],
            attributes[3],
            Integer.parseInt(attributes[4]),
            attributes[5]
        );
    }
}
