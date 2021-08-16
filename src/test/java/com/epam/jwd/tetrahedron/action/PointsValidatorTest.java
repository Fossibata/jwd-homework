package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.action.PointsValidator;
import com.epam.jwd.tetrahedron.exception.NotEnoughArgumentException;
import com.epam.jwd.tetrahedron.exception.NotValidArgumentException;
import com.epam.jwd.tetrahedron.exception.TooManyArgumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsValidatorTest {

    PointsValidator validator = new PointsValidator();
    final String VALID_ARGS = "1.23 3.4 13 11 4 1 3 9 1 10 11 23";
    final String NOT_ENOUGH_ARGS = "1.23 3.4 13 11 4 1";
    final String TOO_MANY_ARGS = "-3 -1 0 5 7 13.0 5 12 11 -1 2 0 12";
    final String NOT_VALID_ARGS = "1.23x 3.4 13 11 4 1 plus some wrong character.";


    @Test
    public void testValidInput() throws NotValidArgumentException, TooManyArgumentException, NotEnoughArgumentException {
       Assert.assertTrue(validator.isValid(VALID_ARGS));

    }
    @Test(expectedExceptions = NotEnoughArgumentException.class)
    public void testNotEnoughArgumentsException() throws NotValidArgumentException, TooManyArgumentException, NotEnoughArgumentException {
        validator.isValid(NOT_ENOUGH_ARGS);

    }
    @Test(expectedExceptions = TooManyArgumentException.class)
    public void testNotEnoughArgumentException() throws NotValidArgumentException, TooManyArgumentException, NotEnoughArgumentException {
        validator.isValid(TOO_MANY_ARGS);
    }
    @Test(expectedExceptions = NotValidArgumentException.class)
    public void testNotValidInputException() throws NotValidArgumentException, TooManyArgumentException, NotEnoughArgumentException {
        validator.isValid(NOT_VALID_ARGS);
    }
}
