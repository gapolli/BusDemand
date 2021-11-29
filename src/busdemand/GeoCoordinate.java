package busdemand;

/**
 * Class used to keep a geographic coordinate.
 *
 * @author gapolli
 */
public class GeoCoordinate extends AbsCoordinate {

    public static double MAX_LAT = -22.53010139355865;
    public static double MIN_LAT = -22.62376718681875;
    public static double MAX_LON = -47.36436520619293;
    public static double MIN_LON = -47.45875788586665;
    public static Image img;
    double lat, lon;

    /**
     * Create a geographic coordinate based in two parameters (latitude and
     * longitude).
     *
     * @param lat the latitude.
     * @param lon the longitude.
     */
    public GeoCoordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", getXrel(), getYRel());
    }

    /**
     * Calculate the relative normalized value for the y geographic coordinate.
     *
     * @return y relative.
     */
    public double getYRel() {
        return 1.0 - (lat - MIN_LAT) / (MAX_LAT - MIN_LAT);
    }

    /**
     * Calculate the relative normalized value for the x geographic coordinate.
     *
     * @return x relative.
     */
    public double getXrel() {
        return (lon - MIN_LON) / (MAX_LON - MIN_LON);
    }

    /**
     * Calculate the x position of the geographic coordinate in a given image
     * file.
     *
     * @return x position.
     */
    @Override
    public int getX() {
        return (int) (getXrel() * img.getWidth());
    }

    /**
     * Calculate the y position of the geographic coordinate in a given image
     * file.
     *
     * @return y position.
     */
    @Override
    public int getY() {
        return (int) (getYRel() * img.getHeight());
    }
}
