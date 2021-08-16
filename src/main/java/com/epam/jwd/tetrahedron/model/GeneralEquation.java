package com.epam.jwd.tetrahedron.model;

import java.util.Objects;

public class GeneralEquation {
    double A;
    double B;
    double C;
    double D;

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

    public double getD() {
        return D;
    }

    public GeneralEquation(double a, double b, double c, double d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    @Override
    public String toString() {
        return "GeneralEquation{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", D=" + D +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralEquation that = (GeneralEquation) o;
        return Double.compare(that.A, A) == 0 &&
                Double.compare(that.B, B) == 0 &&
                Double.compare(that.C, C) == 0 &&
                Double.compare(that.D, D) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(A, B, C, D);
    }
}
