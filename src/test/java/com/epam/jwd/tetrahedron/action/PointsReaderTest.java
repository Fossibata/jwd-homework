package com.epam.jwd.tetrahedron.action;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PointsReaderTest {

    private final PointsReader reader = new PointsReader();
    private final PointsValidator validator = new PointsValidator();

    @Test(expectedExceptions = IOException.class)
    public void testIsCorrectPath() throws IOException {
        String WRONG_PATH = "wrong.txt";

        reader.obtainCheckedData(WRONG_PATH, validator);
    }

    @Test
    public void testObtainCheckedData() throws IOException {
        String CORRECT_PATH = "src/test/resources/data.txt";

        Assert.assertEquals(2, reader.obtainCheckedData(CORRECT_PATH, validator).size());
    }

}
