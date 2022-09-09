package com.ogawalucas.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

    @Test
    public void mustReturnScalene_ifThe3SidesAreDifferentSize() throws ValidationException {
        assertEquals(
            EType.SCALENE,
            new Triangle().getType(2, 3, 4)
        );
    }

    @Test
    public void mustReturnIsosceles_if2SidesAreTheSameSize() throws ValidationException {
        assertEquals(
            EType.ISOSCELES,
            new Triangle().getType(2, 2, 3)
        );
    }

    @Test
    public void mustReturnEquilateral_ifThe3SidesAreTheSameSize() throws ValidationException {
        assertEquals(
            EType.EQUILATERAL,
            new Triangle().getType(1, 1, 1)
        );
    }

    @Test
    public void mustReturnIsosceles_if2SidesAreTheSameSize_case1() throws ValidationException {
        assertEquals(
            EType.ISOSCELES,
            new Triangle().getType(3, 2, 2)
        );
    }

    @Test
    public void mustReturnIsosceles_if2SidesAreTheSameSize_case2() throws ValidationException {
        assertEquals(
            EType.ISOSCELES,
            new Triangle().getType(3, 3, 4)
        );
    }

    @Test
    public void mustReturnIsosceles_if2SidesAreTheSameSize_case3() throws ValidationException {
        assertEquals(
            EType.ISOSCELES,
            new Triangle().getType(4, 3, 3)
        );
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_if1SideIsZero() throws ValidationException {
        new Triangle().getType(0, 3, 4);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_if1SideIsNegative() throws ValidationException {
        new Triangle().getType(-1, 3, 4);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case1() throws ValidationException {
        new Triangle().getType(1, 2, 3);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case2() throws ValidationException {
        new Triangle().getType(1, 3, 2);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case3() throws ValidationException {
        new Triangle().getType(2, 1, 3);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case4() throws ValidationException {
        new Triangle().getType(2, 3, 1);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case5() throws ValidationException {
        new Triangle().getType(3, 1, 2);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsEqualThanThirdSide_case6() throws ValidationException {
        new Triangle().getType(3, 2, 1);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case1() throws ValidationException {
        new Triangle().getType(1, 2, 4);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case2() throws ValidationException {
        new Triangle().getType(1, 4, 2);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case3() throws ValidationException {
        new Triangle().getType(2, 1, 4);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case4() throws ValidationException {
        new Triangle().getType(2, 4, 1);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case5() throws ValidationException {
        new Triangle().getType(4, 1, 2);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifTheSumOf2SidesIsLessThanThirdSide_case6() throws ValidationException {
        new Triangle().getType(4, 2, 1);
    }

    @Test(expected = ValidationException.class)
    public void mustThrowValidationException_ifThe3SidesAre0() throws ValidationException {
        new Triangle().getType(0, 0, 0);
    }
}
