package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.model.Coordinates;

public class TetrahedronValidator {

    private boolean isTetrahedron(Coordinates a, Coordinates b, Coordinates c, Coordinates d,
                                  TetrahedronComputer computer){
       return computer.isTetrahedron(a, b, c, d);
    }
    private boolean isParallelOfCoordinatePlane(Coordinates a, Coordinates b, Coordinates c, Coordinates d,
                                                TetrahedronComputer computer){
        return computer.getTopPoint(a, b, c, d)!=null;
    }

    public boolean isValid(Coordinates a, Coordinates b, Coordinates c, Coordinates d,
                           TetrahedronComputer computer) {
        return isTetrahedron(a,b,c,d,computer) && isParallelOfCoordinatePlane(a,b,c,d,computer);
    }
}
