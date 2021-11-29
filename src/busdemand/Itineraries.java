package busdemand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class used to keep a set of itineraries.
 * 
 * @author gapolli
 */
public class Itineraries {
    private static Itineraries inst = null;
    ArrayList<Itinerary> its = new ArrayList();

    /**
     * Gets the instance.
     * 
     * @return the instance.
     */
    public static Itineraries getInstance() {
        if (inst == null) {
            inst = new Itineraries();
        }
        return inst;
    }

    /**
     * The constructor of the class.
     */
    private Itineraries() {
        try {
            File f = new File("busStops.csv");
            BufferedReader br = Util.getBr(f);
            String line = br.readLine();
            while (line != null) {
                if (line != null && line.split("\"").length > 1) {
                    Itinerary it = new Itinerary(line);
                    its.add(it);
                }
                line = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Itineraries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        String linha = "";
        for (Itinerary it : its) {
            linha += it.toString();
            linha += "\n";
        }
        return linha;
    }

    /**
     * Gets the set of itineraries.
     * 
     * @return a set of itineraries.
     */
    public ArrayList<Itinerary> getIts() {
        return its;
    }

    /**
     * Counts the number of stops in a set of itineraries.
     */
    public void countStops() {
        int count = 0;
        for (int i = 0; i < its.size(); i++) {
            count += its.get(i).stops.size();
        }
        System.out.println("Total stops: " + count);
    }
}
