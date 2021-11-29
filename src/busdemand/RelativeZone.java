package busdemand;

import java.util.Random;

/**
 * Class that create a RelativeZone based on its borders.
 *
 * @author gapolli
 */
public class RelativeZone {
    public final static Random r = new Random(1);
    int id;
    Image img;
    String name;
    double xLeft, xRight, yUp, yDown;
    static int MAXID = 0;

    /**
     * Create a RelativeZone inside a box of borders delimited by xLeft, xRight,
     * yUp and yDown.
     *
     * @param img a image file used for coordinate-based calculations.
     * @param name the name of the RelativeZone.
     * @param xLeft the value of the x in percentage at the left.
     * @param xRight the value of the x in percentage at the right.
     * @param yDown the value of the y in percentage at the bottom.
     * @param yUp the value of the y in percentage at the top.
     */
    public RelativeZone(Image img, String name, double xLeft, double xRight, double yDown, double yUp) {
        this.img = img;
        this.id = MAXID++;
        this.name = name;
        this.xLeft = xLeft / 100;
        this.xRight = xRight / 100;
        this.yUp = yUp / 100;
        this.yDown = yDown / 100;
    }

    /**
     * Generate a coordinate at a random position.
     *
     * @return the coordinate.
     */
    Coordinate getRandomPosition() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        while (x > xRight || x < xLeft || y > yUp || y < yDown) {
            System.out.printf("%f %f %f - %f %f %f\n", xLeft, x, xRight, yDown, y, yUp);
            x = r.nextDouble();
            y = r.nextDouble();
        }
        int xc = (int) (x * img.getWidth());
        int yc = (int) (y * img.getHeight());
        Coordinate c = new Coordinate(xc + " " + yc);
        return c;
    }
}
