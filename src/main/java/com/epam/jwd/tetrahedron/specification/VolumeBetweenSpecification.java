package com.epam.jwd.tetrahedron.specification;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public class VolumeBetweenSpecification implements Specification<TetrahedronRegistrar> {
    private final double from;
    private final double to;

    public VolumeBetweenSpecification(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isExist(TetrahedronRegistrar registrar) {
        double volume = registrar.getVolume();

        return Double.compare(volume, from) != -1 && Double.compare(to, volume) != -1;
    }
}
