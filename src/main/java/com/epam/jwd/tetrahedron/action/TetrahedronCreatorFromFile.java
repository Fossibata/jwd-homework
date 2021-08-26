package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.exception.CreateTetrahedronException;
import com.epam.jwd.tetrahedron.model.Point;
import com.epam.jwd.tetrahedron.model.Tetrahedron;
import com.epam.jwd.tetrahedron.model.TetrahedronFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TetrahedronCreatorFromFile {

    public static Logger LOG = LogManager.getLogger(TetrahedronCreatorFromFile.class);

    public List<Tetrahedron> createFromFile(String pathToFile) {

        PointsReader reader = new PointsReader();
        PointsValidator pointsValidator = new PointsValidator();

        TetrahedronValidator tetrahedronValidator = new TetrahedronValidator();
        TetrahedronComputer computer = new TetrahedronComputer();

        TetrahedronFactory factory = new TetrahedronFactory(tetrahedronValidator, computer);
        List<Tetrahedron> tetrahedronsList = null;

        try {
            List<Point[]> points = reader.obtainCheckedData(pathToFile, pointsValidator);
            if (points == null) return null;
            tetrahedronsList = new ArrayList<>();
            for (Point[] point : points) {
                try {
                    Tetrahedron tmp = factory.create(point[0], point[1], point[2], point[3]);
                    tetrahedronsList.add(tmp);

                    String successfulCreationMessage = String.format("Tetrahedron is create by points %s, %s, %s, %s",
                            point[0].getCoordinates(), point[1].getCoordinates(), point[2].getCoordinates(),
                            point[3].getCoordinates());

                    LOG.info(successfulCreationMessage);
                } catch (CreateTetrahedronException e) {
                    String creationFailMessage = String.format("Tetrahedron is not create by points %s, %s, %s, %s" +
                                    "\n because %s",
                            point[0].getCoordinates(), point[1].getCoordinates(), point[2].getCoordinates(),
                            point[3].getCoordinates(), e.getMessage());
                    LOG.error(creationFailMessage);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return tetrahedronsList;
    }
}
