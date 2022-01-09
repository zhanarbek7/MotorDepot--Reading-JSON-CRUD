import AutoPark.AutoPark;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * Project: MotorDepot
 * Author: Zhanarbek Abdurasulov
 * Date: 9/1/22
 */
public class Main {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args) {
        List<AutoPark> list1 = new ArrayList<>();
        try{
            String data1 = new String(Files.readAllBytes(Path.of("/Users/joesbeast/IdeaProjects/newMotorDepot/firstStage.json")));
            JSONObject json = new JSONObject(data1);
            for(int i = 0; i<json.length();i++){
                JSONObject truck = json.getJSONObject(String.valueOf(i));
                AutoPark car1 = new AutoPark();
                car1.setId(truck.getInt("id"));
                car1.setName(truck.getString("name"));
                car1.setDriver(truck.getString("driver"));
                car1.setState(truck.getString("state"));
                list1.add(car1);
            }
            displayTrucks(list1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void displayTrucks(List<AutoPark> list1){
        System.out.println(ANSI_YELLOW + "-----------------TRUCKS-------------------"+ ANSI_RESET);
        System.out.println("|#id|      Bus      |    Driver   |State |");
        System.out.println("|———|———————————————|—————————————|——————|");
        for(AutoPark autoPark : list1){
            System.out.printf("|%-2s ",autoPark.getId());
            System.out.printf("|%-15s|", autoPark.getName());
            System.out.printf("%-13s|", autoPark.getDriver() + " ");
            System.out.printf("%-6s|",autoPark.getState());
            System.out.println();
        }
    }


}
