package busdemand;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class used to create a set of trips.
 * 
 * @author gapolli
 */
public class Trips {
    ArrayList<Trip> trips;
    private static Trips inst = null;
    Random r = RelativeZone.r;
    public static final int TRIPS_NUMBER = 20;

    public static Trips getInstance() {
        if (inst == null) {
            inst = new Trips();
        }
        return inst;
    }

    /**
     * The constructor of the class Trips.
     */
    private Trips() {
        trips = new ArrayList();
        RelativeZones zones = RelativeZones.getInstance();
        DemandVector d = new DemandVector();
        d.normalize();
        for (int i = 0; i < TRIPS_NUMBER; i++) {
            RelativeZone departure = zones.get(d.getRandomDeparture());
            RelativeZone arrival = zones.get(d.getRandomArrival());
            Trip t = new Trip(departure, arrival);
            trips.add(t);
        }
    }

    /**
     * Not implemented yet.
     */
    public void draw() {
        // something here
    }
}
