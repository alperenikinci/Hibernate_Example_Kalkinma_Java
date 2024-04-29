package com.alperen.repository;

import java.util.List;
import java.util.Optional;

public interface ICrud<T,ID>{
    T save(T entity);
    T update(T entity);
    Iterable<T> saveAll(Iterable<T> entities);
    void delete(T entity);
    void deleteByID(ID id);
    Optional<T> findById(ID id);
    Boolean existsById(ID id);
    List<T> findAll();
    List<T> findByColumnNameAndValue(String columnName, Object value);
    List<T> findAllEntity(T entity);
}
