package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.model.Coordinates;
import com.epam.jwd.tetrahedron.model.GeneralEquation;
import com.epam.jwd.tetrahedron.model.Tetrahedron;

import java.util.*;

public class TetrahedronComputer {

    private final GeneralEquation XOY;
    private final GeneralEquation XOZ;
    private final GeneralEquation YOZ;
    private GeneralEquation oxyz;

    public TetrahedronComputer() {
        this.XOY = new GeneralEquation(0,0,1,0);
        this.XOZ = new GeneralEquation(0,1,0,0);
        this.YOZ = new GeneralEquation(1,0,0,0);
    }

    private Coordinates vectorCoordinates(Coordinates a, Coordinates b){
        return new Coordinates(b.getX() - a.getX(),
                b.getY() - a.getY(),
                b.getZ() - a.getZ());
    }

    private Coordinates crossProduct(Coordinates a, Coordinates b, Coordinates c){
        Coordinates ab = vectorCoordinates(a, b);
        Coordinates ac =  vectorCoordinates(a, c);
        return new Coordinates(ab.getY()*ac.getZ() - ab.getZ()*ac.getY(),
                -1*(ab.getX()*ac.getZ() - ab.getZ()*ac.getX()),
                ab.getX()* ac.getY() - ab.getY()*ac.getX());
    }

    private GeneralEquation computeCoefficientOfGeneralEquation(Coordinates a, Coordinates b,
                                                               Coordinates c){
        Coordinates abXac = crossProduct(a, b, c);
        double coefficientA = abXac.getX();
        double coefficientB = abXac.getY();
        double coefficientC = abXac.getZ();
        double coefficientD = -1*(a.getX()*abXac.getX() + a.getY()*abXac.getY() +
                a.getZ() * abXac.getZ());
        return new GeneralEquation(coefficientA, coefficientB, coefficientC, coefficientD);
    }

    private double computeVolume(Coordinates a, Coordinates b, Coordinates c, Coordinates d){
        Coordinates ab = vectorCoordinates(a, b);
        Coordinates acXcd = crossProduct(a,c,d);
        return   (1.0/6) * Math.abs (ab.getX()* acXcd.getX() + ab.getY()* acXcd.getY() +
                ab.getZ()* acXcd.getZ());
    }

    private double computePlaneSquare(Coordinates a, Coordinates b, Coordinates c){
        Coordinates ab_ac = crossProduct(a,b,c);
        return (1.0 / 2) * Math.sqrt(ab_ac.getX() * ab_ac.getX() + ab_ac.getY() * ab_ac.getY() +
                ab_ac.getZ() * ab_ac.getZ());
    }

    private double computeDeterminant(double [][] arr){

        return arr[0][0] * (arr[1][1] * arr[2][2] - arr[1][2] * arr[2][1]) -
                arr[0][1] * (arr[1][0] * arr[2][2] - arr[1][2] * arr[2][0]) +
                arr[0][2] * (arr[1][0] * arr[2][1] - arr[1][1] * arr[2][0]);

    }

    private Coordinates computeIntersectionPoint(Coordinates a, Coordinates b, Coordinates c,
                                                Coordinates d, GeneralEquation oxyz){

        return computeIntersectionPoint(computeCoefficientOfGeneralEquation(a,b,c),
                computeCoefficientOfGeneralEquation(a,b,d),
                oxyz);

    }
    private Coordinates computeIntersectionPoint(GeneralEquation firstPlane,
                                                GeneralEquation secondPlane, GeneralEquation oxyz){

        double delta = computeDeterminant(new double[][]{{firstPlane.getA(),firstPlane.getB(),firstPlane.getC()},
                {secondPlane.getA(), secondPlane.getB(), secondPlane.getC()},
                {oxyz.getA(),oxyz.getB(),oxyz.getC()}});

        double deltaX = computeDeterminant(new double[][]{{firstPlane.getD(),firstPlane.getB(),firstPlane.getC()},
                {secondPlane.getD(), secondPlane.getB(), secondPlane.getC()},
                {oxyz.getD(),oxyz.getB(),oxyz.getC()}});

        double deltaY = computeDeterminant(new double[][]{{firstPlane.getA(),firstPlane.getD(),firstPlane.getC()},
                {secondPlane.getA(), secondPlane.getD(), secondPlane.getC()},
                {oxyz.getA(),oxyz.getD(),oxyz.getC()}});

        double deltaZ = computeDeterminant(new double[][]{{firstPlane.getA(),firstPlane.getB(),firstPlane.getD()},
                {secondPlane.getA(), secondPlane.getB(), secondPlane.getD()},
                {oxyz.getA(),oxyz.getB(),oxyz.getD()}});

        return new Coordinates(deltaX/delta, deltaY/delta,deltaZ/delta);
    }
    private GeneralEquation findParallelPlane(Coordinates a, Coordinates b, Coordinates c){
        GeneralEquation plane = computeCoefficientOfGeneralEquation(a, b, c);

        if(plane.getA() == 0 && plane.getB()==0 && plane.getC() != 0) {
            oxyz = XOY;
            return plane;
        }
        else if (plane.getA() == 0 && plane.getB() != 0 && plane.getC() == 0) {
            oxyz = XOZ;
            return plane;
        }
        else if (plane.getA() != 0 && plane.getB() == 0 && plane.getC() == 0){
            oxyz = YOZ;
            return plane;
        }
        else {
            return null;
        }
    }


    public double computeVolume(Tetrahedron tetrahedron){
       return computeVolume(tetrahedron.getA().getCoordinates(), tetrahedron.getB().getCoordinates(),
                tetrahedron.getC().getCoordinates(), tetrahedron.getD().getCoordinates());

    }

    public boolean isTetrahedron(Coordinates a, Coordinates b, Coordinates c, Coordinates d){
        return computeVolume(a, b, c, d) != 0;
    }

    public double computeSurfaceSquare(Tetrahedron tetrahedron){

        Coordinates a = tetrahedron.getA().getCoordinates();
        Coordinates b = tetrahedron.getB().getCoordinates();
        Coordinates c = tetrahedron.getC().getCoordinates();
        Coordinates d = tetrahedron.getD().getCoordinates();

        return computePlaneSquare(a, b, c) + computePlaneSquare(a, b, d) + computePlaneSquare(a, c, d) + computePlaneSquare(b ,c ,d);
    }

    public String computeVolumeRatio(Tetrahedron tetrahedron){

        Coordinates top = getTopPoint(tetrahedron.getA().getCoordinates(),tetrahedron.getB().getCoordinates(),
                tetrahedron.getC().getCoordinates(),tetrahedron.getD().getCoordinates());
        List<Coordinates> list = new ArrayList<>(Arrays.asList(tetrahedron.getA().getCoordinates(), tetrahedron.getB().getCoordinates(),
                tetrahedron.getC().getCoordinates(),tetrahedron.getD().getCoordinates()));
        list.remove(top);

        Coordinates b = list.get(0);

        double intersectCoefficient = top.getX() * b.getX() * Math.abs(oxyz.getA()) +
                                      top.getY() * b.getY() * Math.abs(oxyz.getB()) +
                                      top.getZ() * b.getZ() * Math.abs(oxyz.getC());

        if(intersectCoefficient > 0) return "1";

        Coordinates c = list.get(1);
        Coordinates d = list.get(2);

        Coordinates abPoint = computeIntersectionPoint(top,b,c,d,oxyz);
        Coordinates acPoint = computeIntersectionPoint(top,c,b,d,oxyz);
        Coordinates adPoint = computeIntersectionPoint(top,d,c,b,oxyz);

        double allVolume = computeVolume(top, b, c, d);
        double firstVolume = computeVolume(top,abPoint,acPoint,adPoint);
        double firstRatio = allVolume/firstVolume;
        double secondRatio = allVolume/(allVolume - firstVolume);
        return String.format("%.2f : %.2f", firstRatio,secondRatio);
    }

    public  boolean isOnCoordinatePlane(Tetrahedron tetrahedron){

        Coordinates a = tetrahedron.getA().getCoordinates();
        Coordinates b = tetrahedron.getB().getCoordinates();
        Coordinates c = tetrahedron.getC().getCoordinates();
        Coordinates d = tetrahedron.getD().getCoordinates();

        int zeroCount = 0;
        double[][] arr = {{a.getX(), b.getX(), c.getX(), d.getX()},
                {a.getY(), b.getY(), c.getY(), d.getY()},
                {a.getZ(), b.getZ(), c.getZ(), d.getZ()}};
        for (double[] doubles : arr) {
            for (double aDouble : doubles) {
                if (aDouble == 0) {
                    zeroCount++;
                }
            }
            if (zeroCount >= 3) {
                return true;
            } else {
                zeroCount = 0;
            }
        }
        return false;
    }

    public Coordinates getTopPoint(Coordinates a,Coordinates b,Coordinates c,Coordinates d){
        GeneralEquation plane = findParallelPlane(a, b, c);

        if (plane != null) {
            return d;
        }
        plane = findParallelPlane(a, b, d);
        if (plane != null) {
            return c;
        }
        plane = findParallelPlane(a, c, d);
        if (plane != null) {
            return b;
        }
        plane = findParallelPlane(b, c, d);
        if (plane != null) {
            return a;
        }
        return null;
    }



}
