package com.epam.jwd.tetrahedron.repository;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;
import com.epam.jwd.tetrahedron.specification.Specification;

import java.util.ArrayList;
import java.util.List;

public class TetrahedronRegistrarRepository implements Repository<TetrahedronRegistrar> {

    private final List<TetrahedronRegistrar> registrars;

    public TetrahedronRegistrarRepository() {
        registrars = new ArrayList<>();
    }

    public TetrahedronRegistrarRepository(List<TetrahedronRegistrar> registrars) {
        this.registrars = registrars;
    }

    @Override
    public void create(TetrahedronRegistrar registrar) {
        registrars.add(registrar);
    }

    @Override
    public TetrahedronRegistrar read(int index) {
        return registrars.get(index);
    }

    @Override
    public boolean update(TetrahedronRegistrar oldRegistrar, TetrahedronRegistrar newRegistrar) {
        if (registrars.contains(oldRegistrar)) {
            int indexOldRegistrar = registrars.indexOf(oldRegistrar);
            registrars.set(indexOldRegistrar, newRegistrar);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delete(TetrahedronRegistrar registrar) {
        registrars.remove(registrar);
    }

    @Override
    public List<TetrahedronRegistrar> getAll() {
        return registrars;
    }

    @Override
    public List<TetrahedronRegistrar> findBySpecification(Specification<TetrahedronRegistrar> specification) {
        List<TetrahedronRegistrar> specRegistrars = new ArrayList<>();

        for (TetrahedronRegistrar registrar : registrars) {
            if (specification.isExist(registrar)) {
                specRegistrars.add(registrar);
            }
        }
        return specRegistrars;
    }
}
