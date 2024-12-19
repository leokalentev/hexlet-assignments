package exercise;

// BEGIN
public class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getBeginPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public  Point getMidPoint() {
        int newX = (end.getX() + start.getX()) / 2;
        int newY = (end.getY() + start.getY()) / 2;
        return new Point(newX, newY);
    }
}
// END
