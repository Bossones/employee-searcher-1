package ru.croc.employee_searcher.filter;

import static java.util.Objects.isNull;

public enum FilterField {

    ID("id"),
    AGE("age"),
    DEPARTMENT("department");

    private final String field;

    FilterField(String field) {
        this.field = field;
    }

    public static FilterField fromField(String field) {
        if (isNull(field) || field.isBlank()) {
            throw new IllegalArgumentException("field is null or empty");
        }
        for (FilterField filterField : FilterField.values()) {
            if (filterField.field.equalsIgnoreCase(field.substring(2))) {
                return filterField;
            }
        }
        throw new IllegalArgumentException("Unknown field: " + field);
    }

}
