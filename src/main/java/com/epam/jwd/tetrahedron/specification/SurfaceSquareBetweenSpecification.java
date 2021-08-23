package com.epam.jwd.tetrahedron.specification;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public class SurfaceSquareBetweenSpecification implements Specification<TetrahedronRegistrar>{
    private final double from;
    private final double to;

    public SurfaceSquareBetweenSpecification(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isExist(TetrahedronRegistrar registrar) {
        double surfaceSquare = registrar.getSurfaceSquare();

        return Double.compare(surfaceSquare, from) != -1 && Double.compare(to, surfaceSquare) != -1;
    }
}
