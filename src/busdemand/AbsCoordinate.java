package busdemand;

/**
 * Abstract class used to create a coordinate.
 * 
 * @author luismeira
 */
public abstract class AbsCoordinate {
    private static int IDCOUNTER = 0;
    private int ID = IDCOUNTER++;
    public static final double SPEED_BY_FEET = 5.0; // km/h
    public static final double BUS_SPEED = 17.0; // km/h
    public static final double WAIT_TIME = 10.0; // minutes
    public abstract int getX();
    public abstract int getY();

    /**
     * Calculates the time spent in a trip by feet between the actual coordinate
     * and the next one.
     *
     * @param other the next coordinate.
     * @return the time spent (in minutes).
     */
    public double distanceByFootMinutes(AbsCoordinate other) {
        return (distanceMeters(other) / 1000.0) * (60.0 / SPEED_BY_FEET);
    }

    /**
     * Calculates the time spent in a trip by bus between the actual coordinate
     * and the next one.
     *
     * @param other the next coordinate.
     * @return the time spent (in minutes).
     */
    public double distanceByBusMinutes(AbsCoordinate other) {
        return (distanceMeters(other) / 1000.0) * (60.0 / BUS_SPEED);
    }

    /**
     * Calculates the approximate distance spent in a trip between the actual
     * coordinate and the next one.
     *
     * @param other the next coordinate.
     * @return the distance.
     */
    public double distanceMeters(AbsCoordinate other) {
        int dx = this.getX() - other.getX();
        int dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy) * BusDemand.PIXEL_IN_METERS;
    }

    /**
     * Get the id of the actual coordinate.
     *
     * @return the id.
     */
    public int getId() {
        return this.ID;
    }

    /**
     * Get the id stored in the last created coordinate + 1.
     *
     * @return the id.
     */
    public static int getMaxId() {
        return IDCOUNTER;
    }
}
