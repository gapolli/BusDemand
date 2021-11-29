package busdemand;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Class used to write a route in a given background image.
 *
 * @author meira
 */
public class Image {

    /**
     * The image.
     */
    BufferedImage img;

    /**
     * Get the image width.
     *
     * @return width.
     */
    public int getWidth() {
        return img.getWidth();
    }

    /**
     * Get the image height.
     *
     * @return height.
     */
    public int getHeight() {
        return img.getHeight();
    }

    /**
     * Create an imagem object using the background image file.
     *
     * @param file background image file.
     */
    public Image(File file) {
        try {
            this.img = ImageIO.read(file);
        } catch (IOException ex) {
            System.err.println("File not Found:" + file.getAbsolutePath());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Write the current image in the output file.
     *
     * @param output the output file.
     */
    public void WriteFile(File output) {
        try {
            ImageIO.write((BufferedImage) this.img, "png", output);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    /**
     * Write a String in the image.
     *
     * @param text the text to be written.
     */
    public void writeString(String text) {
        int posX = getWidth() / 5;
        int posY = 10 * getHeight() / 12;
        int linesPread = getHeight() / 45;
        Graphics g = this.img.getGraphics();
        g.setColor(Color.black);
        Font newFont = new Font("Courier", Font.PLAIN, (int) (this.getHeight() * 0.02F));
        g.setFont(newFont);
        String list[] = text.split("\n");
        int lineCounter = 0;
        int columCounter = 0;
        Color bgColor = new Color(1f, 1f, 1f, .9f);
        for (String line : list) {
            int x = posX / 30 + posX * columCounter;
            int y = posY + lineCounter * linesPread;
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D rect = fm.getStringBounds(line, g);
            g.setColor(bgColor);
            g.fillRect(x,
                    y - fm.getAscent(),
                    (int) rect.getWidth(),
                    (int) rect.getHeight());
            g.setColor(Color.black);
            g.drawString(line, x, y);
            if (lineCounter++ == 7) {
                lineCounter = 0;
                columCounter++;
            }
        }
    }

    /**
     * Draw a line segment between two coordinates.
     *
     * @param a origin.
     * @param b destiny.
     */
    public void DrawSegment(AbsCoordinate a, AbsCoordinate b) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    }
    
    /**
     * Draw a line segment between two coordinates.
     * 
     * @param a origin.
     * @param b destiny.
     * @param c color.
     */
    public void DrawSegment(AbsCoordinate a, AbsCoordinate b, Color c) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(c);
        graf.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    }
    
    /**
     * Draw a line segment between two coordinates.
     * 
     * @param ax origin's x.
     * @param ay origin's y.
     * @param bx destiny's x.
     * @param by destiny's y.
     */
    public void DrawSegment(int ax, int ay, int bx, int by){
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine(ax, ay, bx, by);
    }

    /**
     * Draw a circular point at a given position.
     *
     * @param x the x position.
     * @param y the y position.
     * @param radius the radius of the circle.
     * @param color the color to be used.
     */
    public void DrawCircularPoint(int x, int y, int radius, Color color) {
        int diameter = 2 * radius;
        Graphics2D g = (Graphics2D) this.img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillOval(x - radius, y - radius, diameter, diameter);
        g.setColor(color.darker());
        g.drawOval(x - radius, y - radius, diameter, diameter);
    }

    /**
     * @deprecated method that draw borders around a given RelativeZone. Use
     * drawZoneBorders(RelativeZone z) instead.
     *
     * @param c a central point in the image file.
     * @param width the width of the border.
     * @param height the height of the border.
     */
    public void drawZoneBorders(Coordinate c, int width, int height) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine(c.x, c.y, c.x + width, c.y);
        graf.drawLine(c.x + width, c.y, c.x + width, c.y + height);
        graf.drawLine(c.x + width, c.y + height, c.x, c.y + height);
        graf.drawLine(c.x, c.y + height, c.x, c.y);
    }

    /**
     * @deprecated method that draw borders around a given Zone. Use
     * drawZoneBorders(RelativeZone z) instead.
     *
     * @param z the given Zone.
     */
    public void drawZoneBorders(Zone z) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine((int) z.x, (int) z.y, (int) (z.x + z.width), (int) z.y);
        graf.drawLine((int) (z.x + z.width), (int) z.y, (int) (z.x + z.width), (int) (z.y + z.height));
        graf.drawLine((int) (z.x + z.width), (int) (z.y + z.height), (int) z.x, (int) (z.y + z.height));
        graf.drawLine((int) z.x, (int) (z.y + z.height), (int) z.x, (int) z.y);
    }

    /**
     * Draw borders around a given RelativeZone.
     *
     * @param z the given RelativeZone.
     */
    public void drawZoneBorders(RelativeZone z) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine((int) (z.xLeft * this.getWidth()), (int) (z.yUp * this.getHeight()), (int) (z.xRight * this.getWidth()), (int) (z.yUp * this.getHeight()));
        graf.drawLine((int) (z.xRight * this.getWidth()), (int) (z.yUp * this.getHeight()), (int) (z.xRight * this.getWidth()), (int) (z.yDown * this.getHeight()));
        graf.drawLine((int) (z.xRight * this.getWidth()), (int) (z.yDown * this.getHeight()), (int) (z.xLeft * this.getWidth()), (int) (z.yDown * this.getHeight()));
        graf.drawLine((int) (z.xLeft * this.getWidth()), (int) (z.yDown * this.getHeight()), (int) (z.xLeft * this.getWidth()), (int) (z.yUp * this.getHeight()));
    }

    /**
     * Draw a line segment between two given trips.
     *
     * @param t the given Trip.
     */
    public void drawTrip(Trip t) {
        Graphics2D graf = (Graphics2D) this.img.getGraphics();
        graf.setStroke(new BasicStroke(getHeight() / 1500.0F));
        graf.setColor(Color.black);
        graf.drawLine(t.start.x, t.start.y, t.end.x, t.end.y);
        DrawCircularPoint(t.start.x, t.start.y, 3, Color.blue);
        DrawCircularPoint(t.end.x, t.end.y, 3, Color.red);
    }

    /**
     * Draw a set of trips.
     *
     * @param t the given set of trips.
     */
    public void drawTrips(Trips t) {
        for (int i = 0; i < t.trips.size(); i++) {
            this.drawTrip(t.trips.get(i));
        }
    }

    /**
     * Draw a set of bus stops and the vectorial route between them.
     *
     * @param aux the given Itinerary.
     */
    public void drawItineraries(Itinerary aux) {
        ArrayList<GeoCoordinate> stops = aux.getStops();
        for (int i = 0; i < stops.size() - 1; i++) {
            GeoCoordinate c1 = stops.get(i);
            GeoCoordinate c2 = stops.get(i + 1);
            int x1 = (int) (c1.getXrel() * this.getWidth());
            int y1 = (int) (c1.getYRel() * this.getHeight());
            if (i == 0) {
                DrawCircularPoint(x1, y1, 5, Color.blue);
            } else {
                DrawCircularPoint(x1, y1, 3, Color.black);
            }
            int x2 = (int) (c2.getXrel() * this.getWidth());
            int y2 = (int) (c2.getYRel() * this.getHeight());
            if (i == stops.size() - 2) {
                DrawCircularPoint(x2, y2, 5, Color.red);
            } else {
                DrawCircularPoint(x2, y2, 3, Color.black);
            }
            DrawSegment(new Coordinate(x1, y1), new Coordinate(x2, y2));
        }
    }

    /**
     * Draw the set of itineraries.
     */
    public void drawItineraries() {

        ArrayList<Itinerary> its = Itineraries.getInstance().getIts();
        for (Itinerary aux : its) {
            drawItineraries(aux);
        }
    }
}
