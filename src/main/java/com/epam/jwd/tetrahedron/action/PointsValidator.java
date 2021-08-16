package com.epam.jwd.tetrahedron.action;

import com.epam.jwd.tetrahedron.exception.NotEnoughArgumentException;
import com.epam.jwd.tetrahedron.exception.NotValidArgumentException;
import com.epam.jwd.tetrahedron.exception.TooManyArgumentException;

public class PointsValidator {


    public boolean isValid(String points)
            throws NotEnoughArgumentException, TooManyArgumentException, NotValidArgumentException {

        String validArguments = "^(-?\\d+(\\.[\\d]+)?\\s){11}(-?\\d+(\\.[\\d]+)?)[\\s]*$";
        String notEnoughArguments = "^(-?\\d+(\\.[\\d]+)?\\s){0,10}(-?\\d+(\\.[\\d]+)?)[\\s]*$";
        String tooManyArguments = "^(-?\\d+(\\.[\\d]+)?\\s){12,}(-?\\d+(\\.[\\d]+)?)[\\s]*$";

        String notEnoughArgumentsMessage = String.format("Not enough Argument in input string: %s", points);
        String tooManyArgumentsMessage = String.format("Too many arguments in input string:%s", points);
        String notValidArgumentsMessage = String.format("Not valid arguments in input string: %s", points);

         if(points.matches(validArguments)){
              return true;
            }
            else if (points.matches(notEnoughArguments)){
                throw new NotEnoughArgumentException(notEnoughArgumentsMessage);
            }
            else if (points.matches(tooManyArguments)){
                throw new TooManyArgumentException(tooManyArgumentsMessage);
            }
            else {
                throw new NotValidArgumentException(notValidArgumentsMessage);
        }
    }
}
