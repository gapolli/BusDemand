package busdemand;

import java.util.Random;

/**
 * Class used to keep a vector of all demands of departure and arrivals.
 * 
 * @author gapolli
 */
public class DemandVector {
    private static final double LL = 0.1; // very low
    private static final double L = 0.25; // low
    private static final double A = 0.5; // average
    private static final double H = 0.75; // high
    private static final double HH = 1.0; // very high
    Random r = new Random(0);
    int n;
    double departureProbability[] = {LL, L, L, A, A, A, A, H, H, H, H, H, H, H, LL};
    double arrivalProbability[] = {HH, H, H, A, L, L, LL, LL, LL, LL, LL, LL, LL, LL, A};
    double departureSum;
    double arrivalSum;
    private double[] departureStart;
    private double[] arrivalStart;
    boolean normalized = false;

    /**
     * The constructor of the class.
     */
    public DemandVector() {
        this.n = 15;
    }

    /**
     * Calculates the sum of all departure probabilities.
     * 
     * @return the sum.
     */
    public double getDepartureSum() {
        for (int i = 0; i < this.departureProbability.length; i++) {
            departureSum += this.departureProbability[i];
        }
        return departureSum;
    }

    /**
     * Calculates the sum of all arrival probabilities.
     * 
     * @return the sum.
     */
    public double getArrivalSum() {
        for (int i = 0; i < this.arrivalProbability.length; i++) {
            arrivalSum += this.arrivalProbability[i];
        }
        return arrivalSum;
    }

    /**
     * Normalizes all sums of departure and arrival probabilities so that the
     * result of that sum is always 1.
     */
    public void normalize() {
        departureSum = getDepartureSum();
        arrivalSum = getArrivalSum();
        print();
        for (int i = 0; i < departureProbability.length; i++) {
            this.departureProbability[i] = this.departureProbability[i] / departureSum;
        }
        for (int i = 0; i < arrivalProbability.length; i++) {
            this.arrivalProbability[i] = this.arrivalProbability[i] / arrivalSum;
        }
        departureStart = new double[this.departureProbability.length];
        for (int i = 0; i < departureStart.length; i++) {
            departureStart[i] = this.departureProbability[i];
        }
        for (int i = 1; i < departureStart.length; i++) {
            departureStart[i] += departureStart[i - 1];
        }
        for (int i = departureStart.length - 1; i > 0; i--) {
            departureStart[i] = departureStart[i - 1];
        }
        departureStart[0] = 0.0;
        arrivalStart = new double[this.arrivalProbability.length];
        for (int i = 0; i < arrivalStart.length; i++) {
            arrivalStart[i] = this.arrivalProbability[i];
        }
        for (int i = 1; i < arrivalStart.length; i++) {
            arrivalStart[i] += arrivalStart[i - 1];
        }
        for (int i = arrivalStart.length - 1; i > 0; i--) {
            arrivalStart[i] = arrivalStart[i - 1];
        }
        arrivalStart[0] = 0.0;
        normalized = true;
        print();
    }

    /**
     * Prints the departure and arrival sums and the normalized data as well.
     */
    void print() {
        for (int i = 0; i < departureProbability.length; i++) {
            System.out.print(" " + departureProbability[i]);
        }
        System.out.println("");
        if (departureStart == null) {
            return;
        }
        for (int i = 0; i < departureStart.length; i++) {
            System.out.print(" " + departureStart[i]);
        }
        System.out.println("");
        System.out.println("---");
        for (int i = 0; i < arrivalProbability.length; i++) {
            System.out.print(" " + arrivalProbability[i]);
        }
        System.out.println("");
        if (arrivalStart == null) {
            return;
        }
        for (int i = 0; i < arrivalStart.length; i++) {
            System.out.print(" " + arrivalStart[i]);
        }
        System.out.println("");
        System.out.println("---");
        int dep = getRandomDeparture();
        int arr = getRandomArrival();
        System.out.println(dep + " to " + arr);
    }

    /**
     * Gets a random departure and returns it.
     * 
     * @return the departure.
     */
    public int getRandomDeparture() {
        double random = this.r.nextDouble();
        for (int i = 0; i < this.n; i++) {
            if (i == this.n - 1) {
                System.out.println(random+" | "+i);
                return i;
            } else {
                if (random >= departureStart[i] && random <= departureStart[i + 1]) {
                    System.out.println(random+" | "+i);
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Gets a random arrival and returns it.
     * 
     * @return the arrival.
     */
    public int getRandomArrival() {
        double random = this.r.nextDouble();
        for (int i = 0; i < this.n; i++) {
            if (i == this.n - 1) {
                System.out.println(random+" | "+i);
                return i;
            } else {
                if (random >= arrivalStart[i] && random <= arrivalStart[i + 1]) {
                    System.out.println(random+" | "+i);
                    return i;
                }
            }
        }
        return -1;
    }
}
