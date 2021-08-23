package com.epam.jwd.tetrahedron.specification;

import com.epam.jwd.tetrahedron.model.Point;
import com.epam.jwd.tetrahedron.model.TetrahedronRegistrar;

public class QuadrantSpecification implements Specification<TetrahedronRegistrar>{
    private final int quadrant;

    public QuadrantSpecification(int quadrant) {
        this.quadrant = quadrant;
    }

    int obtainQuadrant(Point p){
        double x = p.getCoordinates().getX();
        double y = p.getCoordinates().getY();
        double z = p.getCoordinates().getZ();

        int FIRST_QUADRANT = 1;
        int SECOND_QUADRANT = 2;
        int THIRD_QUADRANT = 3;
        int FORTH_QUADRANT = 4;
        int FIFTH_QUADRANT = 5;
        int SIXTH_QUADRANT = 6;
        int SEVENTH_QUADRANT = 7;
        int EIGHTH_QUADRANT = 8;
        int ON_COORDINATE_AXIS = -1;

        if (x > 0 && y > 0 && z > 0) return FIRST_QUADRANT;
        else if (x < 0 && y > 0 && z > 0) return SECOND_QUADRANT;
        else if (x < 0 && y < 0 && z > 0) return THIRD_QUADRANT;
        else if (x > 0 && y < 0 && z > 0) return FORTH_QUADRANT;
        else if (x > 0 && y > 0 && z < 0) return FIFTH_QUADRANT;
        else if (x > 0 && y < 0 && z < 0) return SIXTH_QUADRANT;
        else if (x < 0 && y < 0 && z < 0) return SEVENTH_QUADRANT;
        else if (x < 0 && y > 0 && z < 0) return EIGHTH_QUADRANT;

        return ON_COORDINATE_AXIS;
    }

    @Override
    public boolean isExist(TetrahedronRegistrar registrar) {
        return obtainQuadrant(registrar.getTetrahedron().getA()) == quadrant
                && obtainQuadrant(registrar.getTetrahedron().getB()) == quadrant
                && obtainQuadrant(registrar.getTetrahedron().getC()) == quadrant
                && obtainQuadrant(registrar.getTetrahedron().getD()) == quadrant;
    }
}
