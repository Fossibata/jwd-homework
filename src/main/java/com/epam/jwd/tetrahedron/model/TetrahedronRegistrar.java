package com.epam.jwd.tetrahedron.model;

import com.epam.jwd.tetrahedron.action.TetrahedronComputer;
import com.epam.jwd.tetrahedron.observer.Observer;

import java.util.Objects;

public class TetrahedronRegistrar implements Observer {
    private final TetrahedronComputer computer;
    private final int id;
    private Tetrahedron tetrahedron;
    private double surfaceSquare;
    private double volume;
    private String volumeRatio;
    private boolean isOnCoordinatePlane;

    public TetrahedronRegistrar(Tetrahedron tetrahedron, TetrahedronComputer computer) {
        this.computer = computer;
        this.tetrahedron = tetrahedron;
        this.id = (int) (System.currentTimeMillis() & 0xfffffff);
        initialize(computer);
    }

    private void initialize(TetrahedronComputer computer) {
        this.surfaceSquare = computer.computeSurfaceSquare(tetrahedron);
        this.volume = computer.computeVolume(tetrahedron);
        this.volumeRatio = computer.computeVolumeRatio(tetrahedron);
        this.isOnCoordinatePlane = computer.isOnCoordinatePlane(tetrahedron);
    }

    public Tetrahedron getTetrahedron() {
        return tetrahedron;
    }

    public int getId() {
        return id;
    }

    public double getSurfaceSquare() {
        return surfaceSquare;
    }

    public double getVolume() {
        return volume;
    }

    public String getVolumeRatio() {
        return volumeRatio;
    }

    public boolean isOnCoordinatePlane() {
        return isOnCoordinatePlane;
    }

    @Override
    public String toString() {
        return "TetrahedronRegistrar{" +
                "tetrahedron=" + tetrahedron +
                ", surfaceSquare=" + surfaceSquare +
                ", id=" + id +
                ", volume=" + volume +
                ", volumeRatio='" + volumeRatio + '\'' +
                ", isOnCoordinatePlane=" + isOnCoordinatePlane +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TetrahedronRegistrar that = (TetrahedronRegistrar) o;
        return Double.compare(that.surfaceSquare, surfaceSquare) == 0 &&
                Double.compare(that.volume, volume) == 0 &&
                isOnCoordinatePlane == that.isOnCoordinatePlane &&
                Objects.equals(tetrahedron, that.tetrahedron) &&
                Objects.equals(id, that.id) &&
                Objects.equals(volumeRatio, that.volumeRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tetrahedron, surfaceSquare, id, volume, volumeRatio, isOnCoordinatePlane);
    }

    @Override
    public void update(Tetrahedron tetrahedron) {
        this.tetrahedron = tetrahedron;
        initialize(computer);

    }
}
