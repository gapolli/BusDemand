package busdemand;

import java.util.ArrayList;

/**
 * Class used to keep a set of relative zones.
 * 
 * @author gapolli
 */
public class RelativeZones {
    private ArrayList<RelativeZone> zones = new ArrayList();
    public static Image img = null;
    private static RelativeZones inst = null;

    /**
     * Gets the current instance.
     * 
     * @return the instance.
     */
    public static RelativeZones getInstance() {
        if (inst == null) {
            inst = new RelativeZones();
        }
        return inst;
    }

    /**
     * Create the relative zones.
     */
    private RelativeZones() {
        RelativeZone z1 = new RelativeZone(img, "z1", 52, 67, 27, 60);
        zones.add(z1);
        RelativeZone z2 = new RelativeZone(img, "z2", 27, 52, 27, 60);
        zones.add(z2);
        RelativeZone z3 = new RelativeZone(img, "z3", 67, 83, 27, 60);
        zones.add(z3);
        RelativeZone z4 = new RelativeZone(img, "z4", 20, 60, 60, 80);
        zones.add(z4);
        RelativeZone z5 = new RelativeZone(img, "z5", 55, 83, 14, 27);
        zones.add(z5);
        RelativeZone z6 = new RelativeZone(img, "z6", 31, 55, 14, 27);
        zones.add(z6);
        RelativeZone z7 = new RelativeZone(img, "z3", 0, 27, 27, 60);
        zones.add(z7);
        RelativeZone z8 = new RelativeZone(img, "z8", 30, 60, 80, 100);
        zones.add(z8);
        RelativeZone z9 = new RelativeZone(img, "z9", 60, 83, 60, 85);
        zones.add(z9);
        RelativeZone z10 = new RelativeZone(img, "z10", 83, 100, 50, 75);
        zones.add(z10);
        RelativeZone z11 = new RelativeZone(img, "z11", 83, 100, 24, 50);
        zones.add(z11);
        RelativeZone z12 = new RelativeZone(img, "z12", 60, 83, 0, 14);
        zones.add(z12);
        RelativeZone z13 = new RelativeZone(img, "z13", 24, 51, 0, 14);
        zones.add(z13);
        RelativeZone z14 = new RelativeZone(img, "z14", 0, 31, 14, 27);
        zones.add(z14);
        RelativeZone z15 = new RelativeZone(img, "z15", 90, 100, 0, 22);
        zones.add(z15);
    }

    /**
     * Draw the borders of each RelativeZone.
     * 
     * @param img the image to draw the RelativeZones in.
     */
    public void draw(Image img) {
        for (int i = 0; i < zones.size(); i++) {
            RelativeZone zone = zones.get(i);
            img.drawZoneBorders(zone);
        }
    }

    /**
     * Get the index of a RelativeZone.
     * 
     * @param pos the position of a RelativeZone in the ArrayList.
     * @return the position.
     */
    public RelativeZone get(int pos) {
        return this.zones.get(pos);
    }
}
