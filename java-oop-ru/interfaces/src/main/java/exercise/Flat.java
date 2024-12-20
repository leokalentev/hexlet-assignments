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

    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + (area + balconyArea) + " метров на " + floor + " этаже";
    }

    public int compareTo(Home another) {
        if ((area + balconyArea) > another.getArea()) {
            return 1;
        } else if ((area + balconyArea) < another.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }
}
// END
