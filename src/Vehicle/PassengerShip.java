package Vehicle;
import java.util.List;
import javafx.scene.image.Image;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PassengerShip extends Ship {
    private final int maxCapacity;
    private final int numOfPassengers;
    private final String company;

    public PassengerShip(Pane bGround, int id, int[] location, Image icon, int maxSpeed, List<Port> ports, int [] route,
                         int maxCapacity, int numOfPassengers, String company, CopyOnWriteArrayList<int []> allLocationCoordinates) {
        super(bGround, id, location, icon, maxSpeed, ports, route, allLocationCoordinates);

        this.maxCapacity = maxCapacity;
        this.numOfPassengers = numOfPassengers;
        this.company = company;

        setTypeOfVehicleClass("Passenger Ship");
        getStageOpt().setTitle(getTypeOfVehicleClass());
        Label textLabel = new Label();
        getPaneOpt().getChildren().add(textLabel);
        getPane().setOnMouseClicked(event1 -> {
            textLabel.setText("\n\nMax ShipCapacity: " + this.maxCapacity + ";\t   Num of passengers: " + this.numOfPassengers
                    + ";\nCompany: " + this.company + ";");
            getStageOpt().show();
        });
    }
}
