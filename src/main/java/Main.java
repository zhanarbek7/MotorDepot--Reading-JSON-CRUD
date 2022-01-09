import AutoPark.AutoPark;
import DriverInfo.DriverInfo;
import Service.ServiceImpl;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Project: MotorDepot
 * Author: Zhanarbek Abdurasulov
 * Date: 9/1/22
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args) {
        List<AutoPark> list1 = new ArrayList<>();
        List<DriverInfo> list2 = new ArrayList<>();
        //1st - stage
        //filling up Trucks
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
        try{
            String data2 = new String(Files.readAllBytes(Path.of("/Users/joesbeast/IdeaProjects/newMotorDepot/secondStage.json")));
            JSONObject json2 = new JSONObject(data2);
            for(int i = 0 ;i<json2.length();i++){
                JSONObject dr = json2.getJSONObject(String.valueOf(i));
                DriverInfo driver;
                driver = new DriverInfo();
                i++;
                driver.setId(i);
                i--;
                driver.setDriverId(dr.getString("id"));
                driver.setName(dr.getString("name"));
                list2.add(driver);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        pattern(list1, list2);

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
    public static void displayDrivers(List<DriverInfo> list2){
        System.out.println(ANSI_YELLOW+"----------------Drivers-----------------"+ANSI_RESET);
        System.out.println("|# |driver-id |  Driver |      Bus     |");
        System.out.println("|——|——————————|—————————|——————————————|");
        for (DriverInfo driverInfo : list2) {
            System.out.printf("|%-2s", driverInfo.getId());
            System.out.printf("|%-10s", driverInfo.getDriverId());
            System.out.printf("|%-9s", driverInfo.getName());
            System.out.printf("|%-14s|", driverInfo.getBus());
            System.out.println();
        }
    }
    public static void pattern(List<AutoPark> list1, List<DriverInfo> list2){
        String choice;
        int id;
        while(true){
            try {
                System.out.println(ANSI_PURPLE + "Choose one of the TRUCK: " + ANSI_RESET);
                id = scanner.nextInt();
                id--;
                System.out.println(ANSI_YELLOW + "----------------TRUCK-INF-----------------" + ANSI_RESET);
                System.out.println("N                :" + list1.get(id).getId());
                System.out.println("BUS              :" + list1.get(id).getName());
                System.out.println("Driver           :" + list1.get(id).getDriver());
                System.out.println("State            :" + list1.get(id).getState());
                System.out.println(ANSI_PURPLE + "Press 1 to change or assign new DRIVER" + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Press 2 to start DRIVING" + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Press 3 to start REPAIRING" + ANSI_RESET);
                System.out.println(ANSI_PURPLE + "Press 4 if you wish to stop program" + ANSI_RESET);
                scanner.nextLine();
                choice = scanner.nextLine();
                if(choice.equals("4")){
                    System.exit(0);
                }
                ServiceImpl actions = new ServiceImpl(list1, list2, id);
                switch (choice) {
                    case "1" -> actions.changeDriver(list1, list2, id);
                    case "2" -> actions.startDriving(list1, list2, id);
                    case "3" -> actions.startRepair(list1, list2, id);
                }
                list1 = actions.getList1();
                list2 = actions.getList2();
                displayDrivers(list2);
                displayTrucks(list1);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(ANSI_RED+"Вы обратились к несуществуещему грузовику!\n"+
                        "Общее количество грузовиков: "+list1.size());
            }
        }
    }

}
