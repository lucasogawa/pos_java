package com.ogawalucas.test;

public class Triangle {

    private static final ValidationException EX_NEGATIVES = new ValidationException("No side can be negative!");
    private static final ValidationException EX_ZERO = new ValidationException("No side can be zero!");
    private static final ValidationException EX_SIDE_SIZE = new ValidationException("The sum of two sides must be greater than the third side!");

    public EType getType(int side1, int side2, int side3) throws ValidationException {
        validateSides(side1, side2, side3);

        if (side1 == side2 && side2 == side3) {
            return EType.EQUILATERAL;
        } else if (side1 != side2 && side2 != side3 && side1 != side3) {
            return EType.SCALENE;
        } else {
            return EType.ISOSCELES;
        }
    }

    private void validateSides(int side1, int side2, int side3) throws ValidationException {
        validateNegatives(side1, side2, side3);
        validateZeros(side1, side2, side3);
        validateSumOfSides(side1, side2, side3);
    }

    private void validateNegatives(int side1, int side2, int side3) throws ValidationException {
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw EX_NEGATIVES;
        }
    }

    private void validateZeros(int side1, int side2, int side3) throws ValidationException {
        if (side1 == 0 || side2 == 0 || side3 == 0) {
            throw EX_ZERO;
        }
    }

    private void validateSumOfSides(int side1, int side2, int side3) throws ValidationException {
        if ((side1 + side2 <= side3) || (side2 + side3 <= side1) || (side1 + side3 <= side2)) {
            throw EX_SIDE_SIZE;
        }
    }
}
