package Vehicle;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.image.Image;

public class PassengerAircraft extends Plane {
    private final int maxCapacity;
    private int numOfPassengers;
    void genRandomNumOfPassengers(){
        numOfPassengers = ThreadLocalRandom.current().nextInt(13, maxCapacity);
    }

    public PassengerAircraft(Pane bGround, int id, int[] location, Image icon, int numOfEmployees, double fuel, int maxSpeed,
                             List<Port> airports, int[] route, int maxCapacity, int numOfPassengers,
                             CopyOnWriteArrayList<int[]> allLocationCoordinates) {
        super(bGround, id, location, icon, numOfEmployees, fuel, maxSpeed, airports, route, allLocationCoordinates);

        this.maxCapacity = maxCapacity;
        this.numOfPassengers = numOfPassengers;

        setTypeOfVehicleClass("Passenger Aircraft");
        getStageOpt().setTitle(getTypeOfVehicleClass());
        Label textLabel = new Label();
        getPaneOpt().getChildren().add(textLabel);
        getPane().setOnMouseClicked(event1 -> {
            if(isLeaveTheAirport()){
                genRandomNumOfPassengers();
                setLeaveTheAirport(false);
            }
            textLabel.setText("\n\n\n\nMax Plane Capacity: "+ this.maxCapacity + ";\t   Num of passengers: "
                    + this.numOfPassengers + ";");
            getStageOpt().show();
        });
    }
}