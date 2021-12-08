import java.util.ArrayList;
import java.util.List;

public class Distance {
    double dist;
    int city1ID;
    int city2ID;

    public Distance() {}

    public Distance(double d, int c1, int c2) {
        this.dist = d;
        this.city1ID = c1;
        this.city2ID = c2;
    }

    public double dist() {
        return this.dist;
    }
    public int city1ID() {
        return this.city1ID;
    }
    public int city2ID() {
        return this.city2ID;
    }

    public String toString() {
        return "Cities: " + this.city1ID + " - " + this.city2ID + " , Distance: " + this.dist;
    }
}


// ISSUES
// Used cities are not working
