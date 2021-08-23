package com.epam.jwd.tetrahedron.comparator;

import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

import java.util.Comparator;

public class CoordinateFirstPointComparator implements Comparator<TetrahedronRegistrar> {
    private final String typeOfCoordinate;
    public CoordinateFirstPointComparator(String typeOfCoordinate) {
        this.typeOfCoordinate = typeOfCoordinate;
    }

    @Override
    public int compare(TetrahedronRegistrar o1, TetrahedronRegistrar o2) {
        switch (typeOfCoordinate){
            case "x": return Double.compare(o1.getTetrahedron().getA().getCoordinates().getX(),
                    o2.getTetrahedron().getA().getCoordinates().getX());
            case "y": return Double.compare(o1.getTetrahedron().getA().getCoordinates().getY(),
                    o2.getTetrahedron().getA().getCoordinates().getY());
            case "z": return Double.compare(o1.getTetrahedron().getA().getCoordinates().getZ(),
                    o2.getTetrahedron().getA().getCoordinates().getZ());
            default: return -99;// error or spit fo three classes
        }

    }


}
