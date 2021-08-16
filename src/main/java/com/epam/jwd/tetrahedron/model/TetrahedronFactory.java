package com.epam.jwd.tetrahedron.model;

import com.epam.jwd.tetrahedron.action.TetrahedronComputer;
import com.epam.jwd.tetrahedron.exception.CreateTetrahedronException;
import com.epam.jwd.tetrahedron.action.TetrahedronValidator;

public class TetrahedronFactory {
    private final TetrahedronValidator validator;
    private final TetrahedronComputer computer;

    public TetrahedronFactory(TetrahedronValidator validator, TetrahedronComputer computer) {
        this.validator = validator;
        this.computer = computer;
    }

    public Tetrahedron create(Point a, Point b, Point c, Point d) throws CreateTetrahedronException {

        String creationFailMessage = "Points do not lie in the same plane or " +
                "the base is not parallel to the coordinate plane.";

        if (validator.isValid(a.getCoordinates(), b.getCoordinates(),
                c.getCoordinates(), d.getCoordinates(),computer)) return new Tetrahedron(a, b, c, d);
        else throw new CreateTetrahedronException(creationFailMessage);
    }
}
