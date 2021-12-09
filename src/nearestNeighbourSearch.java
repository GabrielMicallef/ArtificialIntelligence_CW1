import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class nearestNeighbourSearch {// CHECK THE OTHER FILE TO CONFIRM WHY IT IS STILL USING THE SAME CITY

    public static ArrayList<City> cities = new ArrayList<>();
    public static ArrayList<Distance> distances = new ArrayList<>();
    public static ArrayList<Integer> usedCities = new ArrayList<>();
    public static ArrayList<Permutation> bestPaths = new ArrayList<>();

    public static void main(String[] args)throws IOException {
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\testfile1.txt";

        // Get text from file, split it, and return a multi-dimensional Array List
        ArrayList<ArrayList<Integer>> cityData = GetCityData(file_path);

        for (int i=0; i< cityData.size(); i++){
            cities.add(new City(cityData.get(i).get(0),cityData.get(i).get(1),cityData.get(i).get(2)));
            System.out.println(cities.get(i));
        }

        helper();
    }

    // ===================================== METHODS =====================================
    static void helper(){
        List<Integer> tempList = new ArrayList(cities.size() + 1);
        Permutation bestPath = new Permutation(1000000000, tempList);

        double startTime = System.nanoTime();

        computeDistances();

        for(int i = 0; i < cities.size(); i++){
            usedCities.clear();
            City firstCity = new City(cities.get(i).cityID, cities.get(i).x, cities.get(i).y);
            usedCities.add(firstCity.cityID);
            bestPaths.add(getBestPath(firstCity.cityID));
        }

        for(int i = 0; i < bestPaths.size(); i++){
            System.out.println(bestPaths.get(i));
            if (bestPaths.get(i).dist < bestPath.dist){
                bestPath.path = bestPaths.get(i).path;
                bestPath.dist = bestPaths.get(i).dist;
            }
        }

        System.out.println("Shortest Path = " + bestPath.path + " Distance = " + bestPath.dist);

        double endTime = System.nanoTime();

        DurationOfAlgorithm(startTime, endTime);
    }

    static Permutation getBestPath(int firstCityId) {
        int firstCity_ID = firstCityId;
        double totalDistance = 0;
        ArrayList<Integer> bestPath_forId = new ArrayList<>();
        Permutation finalPermutation = new Permutation();

        for(int i = 0; i < (cities.size() + 1); i++){
            if(bestPath_forId.size() == 0){
                bestPath_forId.add(0, firstCity_ID);
            }
            else if(bestPath_forId.size() == cities.size()){
                bestPath_forId.add(firstCity_ID);
            }
            else{
                Distance closestCity = getClosestCity(firstCityId);
                bestPath_forId.add(closestCity.city2ID);
                totalDistance = totalDistance + closestCity.dist;
                usedCities.add(closestCity.city2ID);
                firstCityId = closestCity.city2ID;
            }
        }

        for(int i = 0; i < distances.size(); i++){
            if(distances.get(i).city1ID == bestPath_forId.get(0) && distances.get(i).city2ID == bestPath_forId.get(1) && bestPath_forId.size() == 0){
                totalDistance = totalDistance + distances.get(i).dist;
            }
            else if(distances.get(i).city1ID == bestPath_forId.get(cities.size() - 1) && distances.get(i).city2ID == bestPath_forId.get(cities.size()) && bestPath_forId.size() == (cities.size() + 1)){
                totalDistance = totalDistance + distances.get(i).dist;
            }
        }

        finalPermutation.dist = totalDistance;
        finalPermutation.path = bestPath_forId;

        return finalPermutation;
    }

    static Distance getClosestCity(int city1_id){
        Distance tempCity = new Distance(0, 0, 0);
        Distance closestCity = new Distance(1000000, city1_id, city1_id);

        int first_index = (city1_id * (cities.size() - 1)) - (cities.size() - 1);
        int last_index  = (city1_id * (cities.size() - 1)) - 1;

        boolean used = false;

        for(int i = first_index; i <= last_index; i++){
            used = false;

            for(int j = 0; j < usedCities.size(); j++){
                if (distances.get(i).city2ID == usedCities.get(j)){
                    used = true;
                }
                else if (j == usedCities.size() || (j + 1) == usedCities.size()){ // THIS IS NOT MATCHING WHEN IT SHOULD BECAUSE WE NEED TO CHECK A DIFFERENT WAY
                    if(i != city1_id && used != true){
                        tempCity.dist = distances.get(i).dist;
                        tempCity.city1ID = distances.get(i).city1ID;
                        tempCity.city2ID = distances.get(i).city2ID;

                        if(distances.get(i).dist < closestCity.dist){
                            closestCity.city1ID = distances.get(i).city1ID;
                            closestCity.city2ID = distances.get(i).city2ID;
                            closestCity.dist = distances.get(i).dist;
                        }
                    }
                    else if (distances.get(i).city2ID == usedCities.get(j)){
                        break;
                    }
                }
            }
        }

        return closestCity;
    }

    static void computeDistances(){

        for(int i = 0; i < cities.size(); i++)
        {
            int city1_id = cities.get(i).cityID;
            int x1 = cities.get(i).x;
            int y1 = cities.get(i).y;

            for(int j = 0; j < cities.size(); j++)
            {
                int city2_id = cities.get(j).cityID;
                int x2 = cities.get(j).x;
                int y2 = cities.get(j).y;

                if(city1_id != city2_id){
                    addDistance(city1_id, x1, y1, city2_id, x2, y2);
                }
            }
        }
    }

    static void addDistance(int city1_id, int x1, int y1, int city2_id, int x2, int y2){
        double dist = 0;

        dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        distances.add(new Distance(dist, city1_id, city2_id));
    }

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

    // Gets the time taken for the algorithm to run
    static void DurationOfAlgorithm(double startTime, double endTime)
    {
        double result = 0;

        result = (endTime - startTime) / 1000000;

        System.out.println("Duration: " + result + " milliseconds");
    }
}
