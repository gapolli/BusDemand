package busdemand;

import java.util.ArrayList;

/**
 * Class used to create a itinerary based on a set of coordinates that
 * represents a set of stops.
 * 
 * @author gapolli
 */
public class Itinerary {
    ArrayList<GeoCoordinate> stops = new ArrayList();

    /**
     * The constructor of the class.
     * 
     * @param line the textual line to be read.
     */
    public Itinerary(String line) {
        line = line.replace("\"", "");
        //System.out.println(""+line);
        String list[] = line.split(",");
        for (int i = 1; i < list.length; i = i + 2) {
            double lat = Double.parseDouble(list[i]);
            double lon = Double.parseDouble(list[i + 1]);
            GeoCoordinate aux = new GeoCoordinate(lat, lon);
            stops.add(aux);
        }
        System.out.println("Stops: " + stops.size());
    }

    @Override
    public String toString() {
        String linha = "";
        linha += String.format("MIN_LAT=%f,MAX_LAT=%f, MIN_LON=%f, MAX_LON =%f ",
                GeoCoordinate.MIN_LAT, GeoCoordinate.MAX_LAT,
                GeoCoordinate.MIN_LON, GeoCoordinate.MAX_LON);
        for (GeoCoordinate g : stops) {
            linha += "" + g.toString();
        }
        return linha;
    }

    /**
     * Gets the set of stops.
     * 
     * @return the stops.
     */
    public ArrayList<GeoCoordinate> getStops() {
        return this.stops;
    }
}
