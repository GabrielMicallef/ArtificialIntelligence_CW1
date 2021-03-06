import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.ToIntFunction;

import static java.util.Collections.swap;

public class BruteForce{
    public static void main(String[] args) throws IOException {
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\FinalTestFile2.txt";
        File file = new File(file_path);
        String line;
        List lines = new ArrayList();
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Integer> cityIds = new ArrayList<>();
        ArrayList<Permutation> permutationsFinal = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Process and store all the cities in the text file into an array list of City objects
        while ((line = br.readLine()) != null){

            lines.add(line);

            String[] strCityData = line.split("\\s+");
            int[] intCityData = new int[strCityData.length];

            for(int i = 0; i < intCityData.length; i++){
                intCityData[i] = Integer.parseInt(strCityData[i]);
            }

            cities.add(new City(intCityData[0],intCityData[1],intCityData[2]));
        }

        for(int i=0; i<cities.size(); i++){
            cityIds.add(cities.get(i).cityID);
        }

        List<List<Integer>> permutations = permute(cityIds);

        double startTime = System.nanoTime();

        // A for loop to get the permutations from the list and calculates the distance traveled for each path
        for(int i = 0; i < permutations.size(); i++){
            List<Integer> path =  permutations.get(i);
            List<Double> distances =  new ArrayList<>();
            double dist = 0;


            int city1_num = path.get(0);
            int x1 = 0;
            int y1 = 0;
            int city2_num = path.get(1);
            int x2 = 0;
            int y2 = 0;

            int c1_i_limit = path.size();

            for(int c1_i = 0, c2_i = 1; c1_i < c1_i_limit; c1_i++, c2_i++)
            {
                city1_num = path.get(c1_i);
                if(c2_i == cities.size()){
                    c2_i = 0;
                }
                city2_num = path.get(c2_i);


                x1 = cities.get(city1_num-1).x;
                y1 = cities.get(city1_num-1).y;

                x2 = cities.get(city2_num-1).x;
                y2 = cities.get(city2_num-1).y;

                dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                distances.add(dist);
            }

            double distTotal = 0;

            if(path.size() == cities.size()){
                int lastCityId = path.get(path.size()-1);

                for(int j = 0; j < cities.size(); j++){
                    if(lastCityId == cities.get(j).cityID){
                        x1 = cities.get(j).x;
                        y1 = cities.get(j).y;

                        for(int k = 0; k < cities.size(); k++){
                            if(path.get(0) == cities.get(k).cityID) {
                                x2 = cities.get(k).x;
                                y2 = cities.get(k).y;

                                dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                                distances.add(dist);
                            }
                        }
                    }
                }

                path.add(path.get(0));
            }

            for (int j = 0; j < distances.size(); j++) {
                distTotal = distTotal + distances.get(j);
            }

            permutationsFinal.add(new Permutation(distTotal, path));

        }

        // A for loop to output the shortest path
        for(int i = 0, j = 1; j < permutationsFinal.size(); j++)
        {
            double shortestPath = permutationsFinal.get(i).dist;
            double comp = permutationsFinal.get(j).dist;

            if(comp < shortestPath){
                i = j;
            }

            if(j == permutationsFinal.size() - 1){
                System.out.println("The shortest path is: " + permutationsFinal.get(i).path + ", Dist:" +permutationsFinal.get(i).dist);
                break;
            }
        }

        double endTime = System.nanoTime();

        DurationOfAlgorithm(startTime, endTime);
    }

    // ===================================== METHODS =====================================
    // Returns a multi dimensional List which contains all the permutations
    static List<List<Integer>> permute(ArrayList<Integer> cityIds)
    {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();
        boolean[] usedIds = new boolean[cityIds.toArray().length];

        if(cityIds == null){
            result.add(Collections.singletonList(1));
            return result;
        }

        helper(cityIds, permutation, usedIds, result);

        return result;
    }

    // Populates the Permutations with cities whilst checking if the cities have been used before.
    static void helper(ArrayList<Integer> cityIds, List<Integer> permutation, boolean[] usedIds, List<List<Integer>> result)
    {
        if(permutation.size() == cityIds.size()){
            result.add(new ArrayList<>(permutation));
            return;
        }
        for(int i=0; i < cityIds.size(); ++i){
            if(usedIds[i]){
                continue;
            }
            usedIds[i] = true;
            permutation.add(cityIds.get(i));
            helper(cityIds, permutation, usedIds, result);
            permutation.remove(permutation.size() - 1);
            usedIds[i] = false;
        }
    }

    // Gets the time taken for the algorithm to run
    static void DurationOfAlgorithm(double startTime, double endTime)
    {
        double result = 0;

        result = (endTime - startTime) / 1000000;

        System.out.println("Duration: " + result + " milliseconds");
    }
}



