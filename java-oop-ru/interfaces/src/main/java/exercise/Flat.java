package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return this.area + this.balconyArea;
    }

    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + this.floor + " этаже";
    }

    @Override
    public int compareTo(Home another) {
        if (another instanceof Flat anotherFlat) {
            return Double.compare(this.area, anotherFlat.area);
        }
        throw new IllegalArgumentException("Argument is not an instance of Flat");
    }
}
// END
