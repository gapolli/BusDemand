package busdemand;

/**
 * Class used to create a trip between two distinct zones.
 * 
 * @author gapolli
 */
public class Trip {
    int id;
    static int MAXID = 0;
    Coordinate start, end;

    /**
     * The constructor of the class.
     * 
     * @param startZone the starting zone.
     * @param endZone the ending zone.
     */
    public Trip(RelativeZone startZone, RelativeZone endZone) {
        this.id = MAXID++;
        this.start = startZone.getRandomPosition();
        this.end = endZone.getRandomPosition();
    }

    /**
     * Calculates the distance between the two received zones.
     * 
     * @return the distance.
     */
    public double getDistance() {
        return Math.sqrt(
                Math.pow((this.end.x - this.start.x), 2)
                + Math.pow((this.end.y - this.start.y), 2)
        );
    }
}
