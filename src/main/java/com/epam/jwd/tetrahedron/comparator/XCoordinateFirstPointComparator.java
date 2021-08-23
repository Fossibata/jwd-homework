package com.epam.jwd.tetrahedron.comparator;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

import java.util.Comparator;

public class XCoordinateFirstPointComparator implements Comparator<TetrahedronRegistrar> {

    @Override
    public int compare(TetrahedronRegistrar o1, TetrahedronRegistrar o2) {
        return Double.compare(o1.getTetrahedron().getA().getCoordinates().getX(),
                o2.getTetrahedron().getA().getCoordinates().getX());
    }
}
