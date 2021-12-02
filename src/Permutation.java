import java.util.ArrayList;
import java.util.List;

public class Permutation {
    double dist;
    List<Integer> path = new ArrayList<Integer>();

    public Permutation(double d, List<Integer> p){
        this.dist = d;
        this.path = p;
    }

    public double dist(){return this.dist;}
    public List<Integer> path(){return this.path;}

    @Override
    public String toString() {
        return "Path: " + path + " , " + "Distance: " + dist ;
    }
}