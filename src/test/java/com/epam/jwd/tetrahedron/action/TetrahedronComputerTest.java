package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.action.TetrahedronValidator;
import com.epam.jwd.tetrahedron.exception.CreateTetrahedronException;
import com.epam.jwd.tetrahedron.model.TetrahedronFactory;
import com.epam.jwd.tetrahedron.model.Point;
import com.epam.jwd.tetrahedron.model.Tetrahedron;
import com.epam.jwd.tetrahedron.action.TetrahedronComputer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TetrahedronComputerTest {

    TetrahedronComputer computer = new TetrahedronComputer();
    TetrahedronValidator validator = new TetrahedronValidator();
    Tetrahedron tetrahedron;
    Point a = Point.of(-4, 2, 6);
    Point b = Point.of(2, -3, 0);
    Point c = Point.of(2, -3, 0);
    Point d = Point.of(-5, 2, -4);

    TetrahedronFactory factory = new TetrahedronFactory(validator, computer);

    @Test(expectedExceptions = CreateTetrahedronException.class)
    public void invalidTetrahedronCreation() throws CreateTetrahedronException {
        factory.create(a, b, c, d);
    }

    Point e = Point.of(5, 1, -5);
    Point f = Point.of(1, 1, 2);
    Point g = Point.of(4, 3, 2);
    Point i = Point.of(3, 5, 2);

    {
        try {
            tetrahedron = factory.create(e, f, g, i);
        } catch (CreateTetrahedronException createTetrahedronException) {
            createTetrahedronException.printStackTrace();
        }
    }


    @Test
    public void testComputeVolume() {
        Assert.assertEquals(9.333, (Math.round(computer.computeVolume(tetrahedron) * 1000.0)) / 1000.0);
    }

    @Test
    public void testComputeSurfaceSquare() {
        Assert.assertEquals(42.643, (Math.round(computer.computeSurfaceSquare(tetrahedron) * 1000.0)) / 1000.0);
    }

    @Test
    public void testIsOnCoordinatePlane() {
        Assert.assertFalse(computer.isOnCoordinatePlane(tetrahedron));
    }

    @Test
    public void testComputeVolumeRatio() {
        Assert.assertEquals("2,74 : 1,57", computer.computeVolumeRatio(tetrahedron));
    }

}
