package busdemand;

import java.util.ArrayList;

/**
 * Singleton class used to keep a graph of coordinates.
 * 
 * @author luismeira
 */
public class Graph {
    ArrayList<AbsCoordinate> coords = new ArrayList<>();
    ArrayList<AbsCoordinate> busStops = new ArrayList<>();
    ArrayList<AbsCoordinate> positions = new ArrayList<>();
    private static Graph inst = null;

    /**
     * 
     * @return the instance.
     */
    public static Graph getInstance() {
        if (inst == null) {
            inst = new Graph();
        }
        return inst;
    }

    double w[][];
    int n;

    /**
     * The constructor of the class.
     */
    private Graph() {
        n = AbsCoordinate.getMaxId();
        w = new double[n][n];
        Itineraries its = Itineraries.getInstance();
        for (Itinerary it : its.getIts()) {
            ArrayList<GeoCoordinate> stops = it.getStops();
            for (GeoCoordinate stop : stops) {
                coords.add(stop);
                busStops.add(stop);
            }
        }
        Trips trips = Trips.getInstance();
        for (Trip t : trips.trips) {
            coords.add(t.start);
            coords.add(t.end);
            positions.add(t.start);
            positions.add(t.end);
        }
        for (int i = 0; i < coords.size(); i++) {
            for (int j = 0; j < coords.size(); j++) {
                AbsCoordinate c1 = coords.get(i);
                AbsCoordinate c2 = coords.get(j);
                w[c1.getId()][c2.getId()] = c1.distanceByFootMinutes(c2);
            }
        }
        for (AbsCoordinate c1 : busStops) {
            for (AbsCoordinate c2 : busStops) {
                w[c1.getId()][c2.getId()] += AbsCoordinate.WAIT_TIME;
            }
        }
        for (AbsCoordinate c1 : positions) {
            for (AbsCoordinate c2 : busStops) {
                w[c1.getId()][c2.getId()] += AbsCoordinate.WAIT_TIME / 2;
            }
        }
        for (Itinerary it : its.getIts()) {
            ArrayList<GeoCoordinate> stops = it.getStops();
            for (int i = 0; i < stops.size() - 1; i++) {
                GeoCoordinate stop1 = stops.get(i);
                GeoCoordinate stop2 = stops.get(i + 1);
                w[stop1.getId()][stop2.getId()] = stop1.distanceByBusMinutes(stop2);
                w[stop2.getId()][stop1.getId()] = stop1.distanceByBusMinutes(stop2);
            }
        }
    }

    /**
     * Gets the coordinate of a stop.
     * 
     * @param id the id of the received coordinate.
     * @return the coordinate.
     */
    public AbsCoordinate getCoord(int id) {
        for (AbsCoordinate c1 : coords) {
            if (c1.getId() == id) {
                return c1;
            }
        }
        return null;
    }

    /**
     * Checks if the current coordinate is a bus stop.
     * 
     * @param id the id of the received coordinate.
     * @return true or false.
     */
    public boolean isBusStop(int id) {
        for (AbsCoordinate c1 : busStops) {
            if (c1.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the segment is traveled by bus.
     * 
     * @param a the first coordinate.
     * @param b the second coordinate.
     * @return true or false.
     */
    public boolean isByBus(AbsCoordinate a, AbsCoordinate b) {
        if (w[a.getId()][b.getId()] == a.distanceByBusMinutes(b)) {
            return true;
        }
        return false;
    }
}
