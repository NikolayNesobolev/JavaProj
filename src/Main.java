import Vehicle.*;import Core.Map;
import java.io.InputStream;
import javafx.scene.image.Image;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.stage.Stage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.application.Application;
import java.util.Scanner;

public class Main extends Application {
    private final Image mapImage, passengerAircraftImage, passengerShipImage,
            militaryAircraftImage, carrierImage, civilAirportImage, militaryAirportImage, civilPortImage, militaryPortImage;
    private final AppPane appPane;
    private final Map map;

    public Main() {
        mapImage = new Image(this.getClass().getResourceAsStream("Png/WMap.png"));
        map = new Map(mapImage);
        appPane = new AppPane();

        civilAirportImage = new Image(this.getClass().getResourceAsStream("Png/Civilairport.png"));
        passengerAircraftImage = new Image(this.getClass().getResourceAsStream("Png/PassengAircraft.png"));

        militaryAirportImage = new Image(this.getClass().getResourceAsStream("Png/militAirport.png"));
        militaryAircraftImage = new Image(this.getClass().getResourceAsStream("Png/militAircraft.png"));

        civilPortImage = new Image(this.getClass().getResourceAsStream("Png/CivilPort.png"));
        passengerShipImage = new Image(this.getClass().getResourceAsStream("Png/PassengShip.png"));

        militaryPortImage = new Image(this.getClass().getResourceAsStream("Png/MilitPort.png"));
        carrierImage = new Image(this.getClass().getResourceAsStream("Png/militShip.png"));
    }

    public int brandNewId(int id){
        id++;
        return id;
    }

    public ArrayList<int[]> routesReading(String filename) {
        ArrayList<int []> routeList = new ArrayList<>();
        InputStream in = this.getClass().getResourceAsStream("InformationData/"+filename);
        Scanner scan = new Scanner(in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String [] info = line.split(" ");
            int [] intInfo = new int [info.length];
            for(int i=0; i<info.length; i++){
                intInfo[i] = Integer.parseInt(info[i]);
            }
            routeList.add(intInfo);
        }
        return routeList;
    }

    public ArrayList<Port> portsReading(String filename, Image icon) {
        ArrayList<Port> portList = new ArrayList<>();
        InputStream in = this.getClass().getResourceAsStream("InformationData/"+filename);
        Scanner scan = new Scanner(in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String [] info = line.split(" ");
            portList.add(new Port(map.getPane(),Integer.parseInt(info[0]), new int[]{Integer.parseInt(info[1]),
                    Integer.parseInt(info[2])}, icon, info[3], Boolean.valueOf(info[4])));
        }
        return portList;
    }

    @Override
    public void start(Stage primaryStage) {
        String[] weapon = {"МиГ-29", "Ан-26", "Г-4", "МиГ-21", "Top Gun", "Iron Eagle", "Swat Kats"};
        String[] company = {"Черноморские круизы", "Crystal Cruises", "Seabourn", "ВодоходЪ", "Princess Cruises",
                "Holland America Line", "Carnival Cruise Line", "Royal Caribbean International", "Disney Cruise Line"};
        CopyOnWriteArrayList<int[]> PlaneAllCords = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<int[]> ShipAllCords = new CopyOnWriteArrayList<>();
        List<int[]> passengerShipRouteList = routesReading("InfPassengShip.txt");
        List<int[]> carrierRouteList = routesReading("InfCarrier.txt");
        List<int[]> passengerAircraftRouteList = routesReading("InfPassengAcraft.txt");
        List<int[]> militaryAircraftRoute = routesReading("InfMilitAcraft.txt");

        List<Port> civilPortList = portsReading("InfCivilPort.txt", civilPortImage);
        List<Port> militaryPortList = portsReading("InfMilitPort.txt", militaryPortImage);
        List<Port> civilAirportList = portsReading("InfCivilAPort.txt", civilAirportImage);
        List<Port> militaryAirportList = portsReading("InfMilitAPort.txt", militaryAirportImage);
        List<Port> routePointList = portsReading("DontTouchTheGround.txt", null);
        civilPortList.addAll(routePointList);
        militaryPortList.addAll(routePointList);

        Button addPassengerShip = new Button("Add passenger ship");
        addPassengerShip.setLayoutX(10);
        addPassengerShip.setLayoutY(40);
        addPassengerShip.setOnAction(event3 -> {
            int genRandMaxCapacity = ThreadLocalRandom.current().nextInt(20, 50);
            int genRandNumOfPassengers = ThreadLocalRandom.current().nextInt(7, genRandMaxCapacity);
            int genRandRoute = ThreadLocalRandom.current().nextInt(0, passengerShipRouteList.size());
            int genRandId = ThreadLocalRandom.current().nextInt(1, 30);
            int genRandMaxSpeed = ThreadLocalRandom.current().nextInt(3, 6);
            new Thread((new PassengerShip(map.getPane(), genRandId,
                    civilPortList.get(passengerShipRouteList.get(genRandRoute)[0] - 1).getLocation(),
                    passengerShipImage, genRandMaxSpeed, civilPortList, passengerShipRouteList.get(genRandRoute),
                    genRandMaxCapacity, genRandNumOfPassengers,
                    company[ThreadLocalRandom.current().nextInt(0, company.length)], ShipAllCords))).start();
        });
        appPane.getPane().getChildren().add(addPassengerShip);

        Button addCarrier = new Button("Add carrier");
        addCarrier.setLayoutX(160);
        addCarrier.setLayoutY(40);
        addCarrier.setOnAction(event3 -> {
            int genRandId = ThreadLocalRandom.current().nextInt(1, 30);
            int genRandRoute = ThreadLocalRandom.current().nextInt(0, carrierRouteList.size());
            int genRandMaxSpeed = ThreadLocalRandom.current().nextInt(5, 7);
            new Thread((new Carrier(map.getPane(), genRandId,
                    militaryPortList.get(carrierRouteList.get(genRandRoute)[0] - 1).getLocation(), carrierImage,
                    genRandMaxSpeed, militaryPortList, carrierRouteList.get(genRandRoute),
                    weapon[ThreadLocalRandom.current().nextInt(0, weapon.length)], militaryAircraftImage,
                    militaryAircraftRoute, militaryAirportList, PlaneAllCords, ShipAllCords))).start();
        });
        appPane.getPane().getChildren().add(addCarrier);

        Button addPassengerAircraft = new Button("Add passenger aircraft");
        addPassengerAircraft.setLayoutX(10);
        addPassengerAircraft.setLayoutY(10);
        addPassengerAircraft.setOnAction(event3 -> {
            int genRandMaxCapacity = ThreadLocalRandom.current().nextInt(20, 50);
            int genRandNumOfEmployees = ThreadLocalRandom.current().nextInt(3, 7);
            int genRandNumOfPassengers = ThreadLocalRandom.current().nextInt(7, 13);
            int genRandId = ThreadLocalRandom.current().nextInt(1, 30);
            int genRandRoute = ThreadLocalRandom.current().nextInt(0, passengerAircraftRouteList.size());
            int genRandMaxSpeed = ThreadLocalRandom.current().nextInt(8, 12);
            new Thread((new PassengerAircraft(map.getPane(), genRandId,
                    civilAirportList.get(passengerAircraftRouteList.get(genRandRoute)[0] - 1).getLocation(),
                    passengerAircraftImage, genRandNumOfEmployees, 500, genRandMaxSpeed, civilAirportList,
                    passengerAircraftRouteList.get(genRandRoute), genRandMaxCapacity, genRandNumOfPassengers,
                    PlaneAllCords))).start();
        });
        appPane.getPane().getChildren().add(addPassengerAircraft);

        Button addMilitaryAircraft = new Button("Add military aircraft");
        addMilitaryAircraft.setLayoutX(160);
        addMilitaryAircraft.setLayoutY(10);
        addMilitaryAircraft.setOnAction(event3 -> {
            int genRandRoute = ThreadLocalRandom.current().nextInt(0, militaryAircraftRoute.size());
            int genRandId = ThreadLocalRandom.current().nextInt(1, 30);
            int genRandMaxSpeed = ThreadLocalRandom.current().nextInt(10, 15);
            new Thread((new MilitaryAircraft(map.getPane(), genRandId,
                    militaryAirportList.get(militaryAircraftRoute.get(genRandRoute)[0] - 1).getLocation(),
                    militaryAircraftImage, 5, 500, genRandMaxSpeed, militaryAirportList,
                    militaryAircraftRoute.get(genRandRoute),
                    weapon[ThreadLocalRandom.current().nextInt(0, weapon.length)], PlaneAllCords))).start();
        });
        appPane.getPane().getChildren().add(addMilitaryAircraft);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
