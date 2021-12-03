import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeamSearch {
    public static void main(String[] args)throws IOException {
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\testfile2.txt";
        ArrayList<City> cities = new ArrayList<>();

        // Get text from file, split it, and return a multi-dimensional Array List
        ArrayList<ArrayList<Integer>> cityData = GetCityData(file_path);

        for (int i=0; i< cityData.size(); i++){
            cities.add(new City(cityData.get(i).get(0),cityData.get(i).get(1),cityData.get(i).get(2)));
            System.out.println(cities.get(i));
        }
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

}
