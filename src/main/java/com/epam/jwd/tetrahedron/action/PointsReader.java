package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.exception.NotEnoughArgumentException;
import com.epam.jwd.tetrahedron.exception.NotValidArgumentException;
import com.epam.jwd.tetrahedron.exception.TooManyArgumentException;
import com.epam.jwd.tetrahedron.model.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PointsReader {

    public static Logger LOG = LogManager.getLogger(PointsReader.class);

    private Point[] toPointArray(String input){
        String[] inputSplitBySpace = input.split("\\s");
        double [] doubles = new double[12];
        for (int i = 0; i < inputSplitBySpace.length; i++) {
            doubles[i] = Double.parseDouble(inputSplitBySpace[i]);
        }
        return new Point[]{Point.of(doubles[0],doubles[1],doubles[2]), Point.of(doubles[3],doubles[4],doubles[5]),
        Point.of(doubles[6],doubles[7],doubles[8]),Point.of(doubles[9],doubles[10],doubles[11])};
    }

    public List<Point[]> obtainCheckedData(String path, PointsValidator validator) throws IOException {
        List<String> uncheckedData = Files.readAllLines(new File(path).toPath());
        if(uncheckedData.isEmpty()) return null;

        List<Point[]> checkedData = new ArrayList<>();

        for (String tmp : uncheckedData) {

            try {
                if (validator.isValid(tmp)) {
                    checkedData.add(toPointArray(tmp));
                }
            } catch (NotEnoughArgumentException | TooManyArgumentException | NotValidArgumentException e) {
                LOG.error(e.getMessage());
            }

        }
        return checkedData;
    }

}
