package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }

    @Override
    public int compareTo(Home another) {
        if (another instanceof Cottage anotherCottage) {
            return Double.compare(this.area, anotherCottage.area);
        }
        throw new IllegalArgumentException("Argument is not an instance of Cottage");
    }
}
// END
