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

//        for(int i = 0, j = 1; i < permutationsFinal.size(); i++, j++)
//        {
//        double shortestPerm = permutationsFinal.get(i).dist;
//        double comp = permutationsFinal.get(j).dist;
//
//        if(comp < shortestPerm){
//        i = j;
//        }
//
//        if(j == permutationsFinal.size()){
//
//        System.out.println("The shortest path is: " + permutationsFinal.get(i).path + ", Dist:" +permutationsFinal.get(i).dist);
//        break;
//        }
//        }
//        }