package exercise;

import exercise.exception.CustomException;

// BEGIN
public class Circle {
    Point point;
    int radius;


    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Radius is less than 0");
        }
        return Math.PI * radius * radius;
    }
}
// END
