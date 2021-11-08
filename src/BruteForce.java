import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.swap;

public class BruteForce{
    public static void main(String[] args) throws IOException {
        // Comment innit
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\testfile1.txt";
        File file = new File(file_path);
        String line;
        List lines = new ArrayList();
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Integer> cityIds = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        // Process and store all the cities in the text file into an array list of City objects
        while ((line = br.readLine()) != null){

            lines.add(line);

            String[] strCityData = line.split(" ");
            int[] intCityData = new int[strCityData.length];

            for(int i = 0; i < intCityData.length; i++){
                intCityData[i] = Integer.parseInt(strCityData[i]);
            }

            cities.add(new City(intCityData[0],intCityData[1],intCityData[2]));
        }

        for(int i = 0; i < cities.size(); i++){
            System.out.println(cities.get(i));
        }

        for(int i=0; i<cities.size(); i++){
            cityIds.add(cities.get(i).cityID);
            System.out.println(cityIds.get(i));
        }

        // The distances between every city need to be calculated (even same distances with different order).
        // The total distances of the permutations have to then be compared so that the shortest one is outputted along with the path taken.
        List<List<Integer>> permutations = permute(cityIds);

        for(int i=0; i<permutations.size(); i++){
            System.out.println(permutations.get(i));
        }

    }

    // ===================================== METHODS =====================================
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


    static double calculateDistanceBetweenCities(City city1, City city2){
        double dist = 0;
        int x1 = city1.x();
        int y1 = city1.y();
        int x2 = city2.x();
        int y2 = city2.y();

        dist = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

        return dist;
    }

}


//    static ArrayList<String> getPermutations(ArrayList<Integer> cityIds, int cid){
//        String permutation = "";
//        ArrayList <String> permutations = new ArrayList<>();
//
//        if(cid == cityIds.size()-1){
//            for(int i = 0; i<permutations.size(); i++){
//                for(int j=0; j<cityIds.size(); j++){
//                    System.out.println(permutation);
//                    permutation = permutation + cityIds.get(j) + "; ";
//                }
//                permutations.add(permutation);
//
//            }
//            for(int k=0; k<permutations.size(); k++){
//                System.out.println(permutations.get(k));
//            }
//
//            return permutations;
//        }else{
//            for(int i = cid; i<cityIds.size(); i++)
//            {
//                System.out.println(swap(cityIds, i, cid));
//                getPermutations(cityIds, cid+1);
//                swap(cityIds, i, cid);
//            }
//        }
//
//        return permutations;
//    }




