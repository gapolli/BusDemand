package busdemand;

/**
 * Class used to keep a cartesian coordinate in 2D.
 *
 * @author meira
 */
public class Coordinate extends AbsCoordinate {

    /**
     * Point in R^2.
     */
    public final int x, y;

    /**
     * Create a coordinate based in a line with two values. The line 5014 3585
     * represents the coordinate (5014,3585).
     *
     * @param line the line to be read.
     */
    public Coordinate(String line) {
        String list[] = line.split(" ");
        x = Integer.parseInt(list[0]);
        y = Integer.parseInt(list[1]);
    }

    /**
     * Create a coordinate based in two values.
     *
     * @param x the x value of a coordinate.
     * @param y the y value of a coordinate.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x value of a coordinate.
     *
     * @return x.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Get the y value of a coordinate.
     *
     * @return y.
     */
    @Override
    public int getY() {
        return y;
    }
}
