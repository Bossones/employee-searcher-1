package ru.croc.employee_searcher.filter;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

public class FilterParser {

    private final String[] args;

    public FilterParser(String[] args) {
        this.args = args;
    }

    public List<Filter> parse() {
        List<Filter> filters = new ArrayList<>();
        if (isNull(args) || args.length == 0) {
            return filters;
        }
        for (int i = 0; i < args.length; i++) { // --department[0] Marketing[1] --age[2] 30[3] --id someId
            final String field = args[i];
            final String value = args[++i];
            filters.add(new Filter(FilterField.fromField(field), value));
        }
        return filters;
    }
}
