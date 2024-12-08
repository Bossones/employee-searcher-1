package ru.croc.employee_searcher;

import ru.croc.employee_searcher.dto.Employee;
import ru.croc.employee_searcher.dto.FilteredEmployee;
import ru.croc.employee_searcher.filter.EmployeeFilter;
import ru.croc.employee_searcher.mapper.EmployeeToFilteredEmployeeMapper;
import ru.croc.employee_searcher.mapper.StringToEmployeeMapper;
import ru.croc.employee_searcher.validator.EmployeeValidator;
import ru.croc.employee_searcher.validator.Validator;
import ru.croc.employee_searcher.validator.attribute.AgeValidator;
import ru.croc.employee_searcher.validator.attribute.DepartmentValidator;
import ru.croc.employee_searcher.validator.attribute.EmailValidator;
import ru.croc.employee_searcher.validator.attribute.NameValidator;
import ru.croc.employee_searcher.validator.attribute.SurnameValidator;
import ru.croc.employee_searcher.validator.attribute.UuidValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class EmployeeSearcher {
    private static final String RESOURCE_PATH = EmployeeSearcher.class.getClassLoader().getResource("").getPath();
    private static final String PROPERTIES_PATH = RESOURCE_PATH + "searcher.properties";
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final EmployeeToFilteredEmployeeMapper mapper;
    private final StringToEmployeeMapper stringToEmployeeMapper;
    private final EmployeeFilter employeeFilter;
    private final EmployeeValidator employeeValidator;

    public EmployeeSearcher(EmployeeToFilteredEmployeeMapper mapper,
                            StringToEmployeeMapper stringToEmployeeMapper,
                            EmployeeFilter employeeFilter,
                            EmployeeValidator employeeValidator) {
        this.mapper = mapper;
        this.stringToEmployeeMapper = stringToEmployeeMapper;
        this.employeeFilter = employeeFilter;
        this.employeeValidator = employeeValidator;
    }

    public static void main(String[] args) throws IOException {
        final List<Validator<Employee>> attributesValidators = List.of(
            new UuidValidator(),
            new NameValidator(),
            new SurnameValidator(),
            new EmailValidator(),
            new AgeValidator(),
            new DepartmentValidator()
        );
        final EmployeeSearcher employeeSearcher = new EmployeeSearcher(
            new EmployeeToFilteredEmployeeMapper(),
            new StringToEmployeeMapper(),
            new EmployeeFilter(args),
            new EmployeeValidator(attributesValidators)
        );
        employeeSearcher.start();
    }

    public void start() throws IOException {
        final Path pathToResource = Path.of(RESOURCE_PATH, properties.getProperty("employee_path"));
        final Path pathToValid = Path.of(RESOURCE_PATH, properties.getProperty("validated_path"));
        final Path pathToInvalid = Path.of(RESOURCE_PATH, properties.getProperty("invalid_path"));
        try (
            Stream<String> lines = Files.lines(pathToResource);
            Writer validatedWriter = Files.newBufferedWriter(pathToValid, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Writer invalidWriter = Files.newBufferedWriter(pathToInvalid, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        ) {
            lines.map(stringToEmployeeMapper::map)
                .filter(employeeFilter::filterBy)
                .forEach(employee -> {
                    final boolean validated = employeeValidator.validate(employee);
                    try {
                        if (validated) {
                            final FilteredEmployee filteredEmployee = mapper.map(employee);
                            validatedWriter.write(filteredEmployee.toString());
                            validatedWriter.write('\n');
                            return;
                        }
                        invalidWriter.write(employee.toString());
                        invalidWriter.write('\n');
                    } catch (IOException ioException) {
                        throw new RuntimeException(ioException);
                    }
                });
        }
    }
}
