/*
* References:
*
* https://moovitapp.com/index/pt-br/transporte_p%C3%BAblico-lines-Limeira-4161-908539
* https://zephyrtransport.github.io/zephyr-directory/
* https://github.com/davidbailey/dpd
*/
package busdemand;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/**
 * The main class of the software
 *
 * @author gapolli
 */
public class BusDemand {

    File fIn, fOut;
    Image img;
    //static DemandGraph d = new DemandGraph();

    /**
     * This factor must be 1.0 if the high resolution image is in use. Comment
     * it based on the image loaded.
     */
    //public static final double CORRECTION_FACTOR = 13.95024077;
    //public static final double CORRECTION_FACTOR = 1.0;
    /**
     * The estimate distance between pixels in the high resoluton image
     */
    public static final double PIXEL_IN_METERS = 14;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BusDemand b = new BusDemand();
        b.execute();
        //DemandGraph d = new DemandGraph();
        //d.normalize();
    }

    /**
     * The main execution block of the software
     */
    public void execute() {
        this.fIn = new File("limeiraSmall.png");
        this.fOut = new File("Out.png");
        this.img = new Image(fIn);

        Itineraries.getInstance().countStops();
        /*this.img.drawItineraries();
        this.img.WriteFile(fOut);
        System.exit(0);*/
        System.out.println("Actual Max Id: " + AbsCoordinate.getMaxId());
        //Trips.getInstance();
        //System.out.println(AbsCoordinate.getMaxId());

        RelativeZones.img = img;
        GeoCoordinate.img = img;
        Itineraries it = Itineraries.getInstance();
        RelativeZones.getInstance();
        Trips.getInstance();
        AllPairShortestPath.getInstance();
        System.out.println("Actual Max Id: " + AbsCoordinate.getMaxId());

        //GeoCoordinate.img = img;
        //this.zones = new RelativeZones(this.img);
        //this.r = RelativeZone.r;
        //d.print();
        //Trips t = createTrips(1000);
        Trips t = Trips.getInstance();
        img.drawTrips(t);
        //img.drawItineraries();
        img.WriteFile(fOut);
        Itineraries.getInstance().countStops();
        AllPairShortestPath dist = AllPairShortestPath.getInstance();
        //System.out.println("---- " + dist.getDist(0, 1));

        for (int i = 0; i < t.trips.size(); i++) {
            int trip = i;
            int tripStart = t.trips.get(trip).start.getId();
            int tripEnd = t.trips.get(trip).end.getId();
            int tripStartX = t.trips.get(trip).start.getX();
            int tripStartY = t.trips.get(trip).start.getY();
            int tripEndX = t.trips.get(trip).end.getX();
            int tripEndY = t.trips.get(trip).end.getY();

            System.out.println("-----");
            System.out.println("Trip: " + i);
            System.out.println("Time: " + dist.getDist(tripStart, tripEnd));
            System.out.println("Id: " + tripStart + " Start: (" + tripStartX + ", " + tripStartY + ")");
            System.out.println("Id: " + tripEnd + " End: (" + tripEndX + ", " + tripEndY + ")");

            ArrayList<Integer> path = AllPairShortestPath.getInstance().path(tripStart, tripEnd);
            System.out.print("Path: ");
            for (Integer x : path) {
                System.out.print(x + " --> ");
            }
            System.out.println("");

            this.fOut = new File("Path" + i + ".png");
            Image image = new Image(this.fIn);

            for (Integer x : path) {
                AbsCoordinate a = Graph.getInstance().getCoord(x);
                if (Graph.getInstance().isBusStop(x)) {
                    image.DrawCircularPoint(a.getX(), a.getY(), 3, Color.blue);
                } else {
                    image.DrawCircularPoint(a.getX(), a.getY(), 3, Color.yellow);
                }
            }
            for (int j = 0; j < path.size() - 1; j++) {
                AbsCoordinate a = Graph.getInstance().getCoord(path.get(j));
                AbsCoordinate b = Graph.getInstance().getCoord(path.get(j + 1));
                if (Graph.getInstance().isByBus(a, b)) {
                    image.DrawSegment(a, b, Color.blue);
                } else {
                    image.DrawSegment(a, b, Color.yellow);
                }
            }
            image.WriteFile(fOut);
        }

        //System.exit(0);
        int top = 1;
        ArrayList<Itinerary> its = Itineraries.getInstance().getIts();
        for (Itinerary aux : its) {
            this.fOut = new File("Out" + top + ".png");
            this.img = new Image(fIn);
            img.drawItineraries(aux);
            img.WriteFile(fOut);

            top++;
        }
        img.WriteFile(fOut);
    }
}