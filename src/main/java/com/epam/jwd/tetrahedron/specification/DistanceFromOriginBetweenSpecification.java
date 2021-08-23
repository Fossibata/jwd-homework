package com.epam.jwd.tetrahedron.specification;

import com.epam.jwd.tetrahedron.model.Coordinates;
import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public class DistanceFromOriginBetweenSpecification implements Specification<TetrahedronRegistrar>{
    private final double from;
    private final double to;

    public DistanceFromOriginBetweenSpecification(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isExist(TetrahedronRegistrar registrar) {
        Coordinates[] arrayPointTetrahedron = {registrar.getTetrahedron().getA().getCoordinates(),
                registrar.getTetrahedron().getB().getCoordinates(),
                registrar.getTetrahedron().getC().getCoordinates(),
                registrar.getTetrahedron().getD().getCoordinates()};
        boolean isDistance = false;

        for (Coordinates coordinates : arrayPointTetrahedron) {
            double distance = Math.sqrt(coordinates.getX() * coordinates.getX() +
                    coordinates.getY() * coordinates.getY() +
                    coordinates.getZ() * coordinates.getZ());

            int compareFrom = Double.compare(from, distance);
            int compareTo = Double.compare(distance, to);

            if(compareFrom == 1) {
                return false;
            }
            else if (compareTo != 1){
                isDistance = true;
            }
      }
        return isDistance;
    }
}
