package com.epam.jwd.tetrahedron.comparator;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

import java.util.Comparator;

public class YCoordinateFirstPointComparator implements Comparator<TetrahedronRegistrar> {

    @Override
    public int compare(TetrahedronRegistrar o1, TetrahedronRegistrar o2) {
        return Double.compare(o1.getTetrahedron().getA().getCoordinates().getY(),
                o2.getTetrahedron().getA().getCoordinates().getY());
    }
}
