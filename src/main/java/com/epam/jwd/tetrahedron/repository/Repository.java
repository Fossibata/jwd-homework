package com.epam.jwd.tetrahedron.repository;

import com.epam.jwd.tetrahedron.specification.Specification;

import java.util.List;

public interface Repository<T> {

    void create(T t);

    T read(int index);

    boolean update(T t, T p);

    void delete(T t);

    List<T> getAll();

    List<T> findBySpecification(Specification<T> specification);


}
