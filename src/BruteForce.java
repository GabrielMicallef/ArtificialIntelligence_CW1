import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BruteForce{
    public static void main(String[] args) throws IOException {
        // Comment innit
        String file_path = "C:\\Users\\Gabriel\\IdeaProjects\\ArtificialIntelligence_CW1\\Test Files\\testfile1.txt";
        File file = new File(file_path);
        String line;
        List lines = new ArrayList();
        ArrayList<City> cities = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null){

            lines.add(line);

            String[] strCityData = line.split(" ");
            int[] intCityData = new int[strCityData.length];

            for(int i = 0; i < intCityData.length; i++){
                intCityData[i] = Integer.parseInt(strCityData[i]);
                //System.out.println(intCityData[i]);
            }

            cities.add(new City(intCityData[0],intCityData[1],intCityData[2]));
        }

        for(int i = 0; i < cities.size(); i++){
            System.out.println(cities.get(i));
        }
    }
}




