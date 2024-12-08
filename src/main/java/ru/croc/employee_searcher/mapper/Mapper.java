package ru.croc.employee_searcher.mapper;

public interface Mapper<T,R> {

    R map(T t);
}
