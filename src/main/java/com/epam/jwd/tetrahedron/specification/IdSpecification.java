package com.epam.jwd.tetrahedron.specification;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public class IdSpecification implements Specification<TetrahedronRegistrar> {
    private final int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean isExist(TetrahedronRegistrar registrar) {
        return registrar.getId() == id;
    }
}
