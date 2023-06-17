package ru.stqa.java_learn.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 1);
        Assert.assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 1);
        double expectedResult = 1;
        double actualResult = p1.distance(p2);
        double eps = 0.000001;
        Assert.assertTrue((Math.abs(expectedResult - actualResult) < eps));
    }
}