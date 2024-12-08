package ru.croc.employee_searcher.filter;

import ru.croc.employee_searcher.dto.Employee;

import java.util.List;
import java.util.UUID;

public class EmployeeFilter {

    private final FilterParser filterParser;
    private final String[] args;
    private final List<Filter> filters;

    public EmployeeFilter(String[] args) {
        this.filterParser = new FilterParser(args);
        this.args = args;
        this.filters = this.filterParser.parse();
    }

    public List<Employee> filterBy(List<Employee> employees) {
        return employees.stream().filter(this::filterBy).toList();
    }

    public boolean filterBy(Employee employee) {
        boolean filtered = true;
        for (Filter filter : filters) {
            filtered &= switch (filter.filterField()) {
                case ID -> UUID.fromString(filter.value()).equals(employee.Id());
                case AGE -> filter.value().equals(String.valueOf(employee.age()));
                case DEPARTMENT -> filter.value().equalsIgnoreCase(employee.department());
            };
        }
        return filtered;
    }
}
