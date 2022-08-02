package Vehicle;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.control.Button;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.image.Image;

public class Carrier extends Ship{
    private final String weapon;

    public Carrier(Pane bGround, int id, int[] location, Image icon, int maxSpeed, List<Port> ports, int [] route,
                   String weapon, Image militaryIcon, List<int[]> aircraftRoute, List<Port> militaryPortList,
                   CopyOnWriteArrayList<int []> planeLocation, CopyOnWriteArrayList<int []> allLocationCoordinates) {
        super(bGround, id, location, icon, maxSpeed, ports, route, allLocationCoordinates);

        this.weapon = weapon;

        getStageOpt().setTitle(getTypeOfVehicleClass());
            Button addPlane = new Button("Add new Military Aircraft");
            addPlane.setLayoutX(270);
            addPlane.setLayoutY(40);
            addPlane.setOnAction(event4 -> {
                int genRandRoute = ThreadLocalRandom.current().nextInt(0,aircraftRoute.size());
                int genRandId = ThreadLocalRandom.current().nextInt(1, 30);
                int genRandMaxSpeed = ThreadLocalRandom.current().nextInt(10, 15);
                new Thread((new MilitaryAircraft(getBGround(), genRandId, getLocation(), militaryIcon, 5,5,
                        genRandMaxSpeed, militaryPortList, aircraftRoute.get(genRandRoute), this.weapon, planeLocation))).start();
                getStageOpt().close();
            });
        Label textLabel = new Label();
        getPaneOpt().getChildren().add(textLabel);
        getPane().setOnMouseClicked(event1 -> {
            textLabel.setText("\n\nMilitary Aircraft weapon: "+ weapon);
            getStageOpt().show();
        });
        getPaneOpt().getChildren().add(addPlane);
    }
}
