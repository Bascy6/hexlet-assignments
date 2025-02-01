package exercise;

// BEGIN
public class Segment {
    private final Point point1;
    private final Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return new Point(point1.getX(), point1.getY());
    }

    public Point getEndPoint() {
        return new Point(point2.getX(), point2.getY());
    }

    public Point getMidPoint() {
        return new Point((point1.getX() + point2.getX()) / 2,
                (point2.getY() + point2.getY()) / 2);
    }
}
// END
