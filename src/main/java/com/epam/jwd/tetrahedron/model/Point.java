package com.epam.jwd.tetrahedron.model;

import java.util.Objects;

public class Point {
    Coordinates coordinates;

    private Point(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public static Point of(double x, double y, double z) {
        Coordinates tmp = new Coordinates(x, y, z);
        return new Point(tmp);
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(coordinates, point.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}