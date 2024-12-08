package ru.croc.employee_searcher.mapper;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.dto.FilteredEmployee;

public class EmployeeToFilteredEmployeeMapper implements Mapper<Employee, FilteredEmployee> {

    @Override
    public FilteredEmployee map(Employee employee) {
        return new FilteredEmployee(
            employee.Id(),
            employee.name(),
            employee.surname(),
            employee.email(),
            employee.age(),
            employee.department(),
            null
        );
    }
}
