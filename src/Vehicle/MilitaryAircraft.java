package Vehicle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class MilitaryAircraft extends Plane{
    private final String weapon;

    public MilitaryAircraft(Pane bGround, int id, int [] location, Image icon, int numOfEmployees, double fuel, int maxSpeed,
                            List<Port> airports, int [] route, String weapon, CopyOnWriteArrayList<int []> allLocationCoordinates) {
        super(bGround, id, location, icon, numOfEmployees, fuel, maxSpeed, airports, route, allLocationCoordinates);

        this.weapon = weapon;

        setTypeOfVehicleClass("MilitaryAircraft");
        getStageOpt().setTitle(getTypeOfVehicleClass());
        Label textLabel = new Label();
        getPaneOpt().getChildren().add(textLabel);
        getPane().setOnMouseClicked(event1 -> {
            textLabel.setText("\n\n\n\nWeapon: " + this.weapon);
            getStageOpt().show();
        });
    }
}