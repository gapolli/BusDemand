package busdemand;

/**
 * @deprecated Class that create a Zone based on its location, width and height.
 *
 * @author gapolli
 */
public class Zone {
    int id;
    String name;
    double x, y, width, height;
    static int MAXID = 0;

    /**
     * Create a Zone at the coordinate (x,y) with the width and height given.
     * 
     * @param name the name of the Zone.
     * @param x the x position.
     * @param y the y position.
     * @param width its width.
     * @param height its height.
     */
    public Zone(String name, double x, double y, double width, double height) {
        this.id = MAXID++;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
