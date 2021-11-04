import java.util.ArrayList;

public class City {
    int cityID;
    int x;
    int y;

    public City(int id, int x, int y) {
        this.cityID = id;
        this.x = x;
        this.y = y;
    }

    public int cityID(){
        return this.cityID;
    }
    public int x(){
        return this.x;
    }
    public int y(){
        return this.y;
    }

    @Override
    public String toString() {
        return "ID: " + cityID + " , " + "X: " + x + " , " + "Y: " + y ;
    }

}
