import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeamSearch {

    public static ArrayList<City> cities = new ArrayList<>();
    public static ArrayList<Distance> distances = new ArrayList<>();
    public static ArrayList<Integer> usedCities = new ArrayList<>();
    public static ArrayList<Integer> bestPath = new ArrayList<>();

    public static void main(String[] args)throws IOException {
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\testfile2.txt";

        // Get text from file, split it, and return a multi-dimensional Array List
        ArrayList<ArrayList<Integer>> cityData = GetCityData(file_path);

        for (int i=0; i< cityData.size(); i++){
            cities.add(new City(cityData.get(i).get(0),cityData.get(i).get(1),cityData.get(i).get(2)));
            System.out.println(cities.get(i));
        }

        helper();
    }


    // ===================================== METHODS =====================================
    public static ArrayList<ArrayList<Integer>> GetCityData(String filePath) {
        String line;
        List lines = new ArrayList();
        File file = new File(filePath);
        ArrayList<String> strCities = new ArrayList<>();
        ArrayList<Integer> intCities = new ArrayList<>();;
        ArrayList<ArrayList<Integer>> cities = new ArrayList<ArrayList<Integer> >();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Process and store all the cities in the text file into an array list of City objects
            while ((line = br.readLine()) != null) {

                lines.add(line);

                String[] strCityData = line.split("\\s+");

                cities.add(new ArrayList<Integer>(Arrays.<Integer>asList
                        (Integer.parseInt(strCityData[0]), Integer.parseInt(strCityData[1]), Integer.parseInt(strCityData[2]))));
            }
        }
        catch (IOException Ex) {
            System.out.println(Ex);
        }

        return cities;
    }

    static void helper(){

        //ArrayList<Integer> bestPath = new ArrayList<>();

        for(int i = 0; i < cities.size(); i++){
            usedCities.clear();
            City firstCity = new City(cities.get(i).cityID, cities.get(i).x, cities.get(i).y);
            usedCities.add(firstCity.cityID);
            //cityIds.add(firstCity.cityID);
            bestPath = getBestPath(firstCity);

            for(int j = 0; j < bestPath.size(); j++){
                System.out.println(bestPath.get(i));
            }
        }
    }

    static ArrayList<Integer> getBestPath(City firstCity){

        ArrayList<Integer> bestPath = new ArrayList<>();

        bestPath.add(firstCity.cityID);

        // Add get the
        for(int i = 0; i < (cities.size()); i++){
            if(cities.get(i).cityID == bestPath.get(bestPath.size()-1)){
                City City1 = cities.get(i);
                int city2_ID = getClosestCity(City1);
                bestPath.add(city2_ID);
            }
        }
        return bestPath;
    }

    static Integer getClosestCity (City City1){
        int result = 0;
        int city1_id = City1.cityID;
        int x1 = City1.x;
        int y1 = City1.y;

        for(int i = 0; i < cities.size(); i++){
            for(int j = 0; j < cities.size(); j++){
                if(cities.get(i).cityID != usedCities.get(j)){ // The error is the usedCities arrayList not being as large j in the second loop. Most likely will need to create another method to compare distances and make sure that the next city is not used at the same time.
                    int city2_id = cities.get(i).cityID;
                    int x2 = cities.get(i).x;
                    int y2 = cities.get(i).y;

                    addDistance(city1_id, x1, y1, city2_id, x2, y2);
                }
            }

            for(int j = 0; j < distances.size(); j++){
                for(int k = 0; k < distances.size(); k++){
                    if(distances.get(j).dist < distances.get(k).dist){
                        Distance distToClosest = new Distance(distances.get(j).dist,
                                distances.get(j).city1ID, distances.get(j).city2ID);
                        addClosestCity(distances.get(j).city2ID);

                        result = distances.get(j).city2ID;
                    }
                }
            }
        }

        return result;
    }

    static void addDistance(int city1_id, int x1, int y1, int city2_id, int x2, int y2){
        double dist = 0;

        dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        distances.add(new Distance(dist, city1_id, city2_id));
    }

    static void addClosestCity(int closestCity){
        if(bestPath.size() <= (cities.size()+1))
        {
            bestPath.add(closestCity);
        }
    }
}
