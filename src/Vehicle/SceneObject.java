package Vehicle;
import javafx.scene.layout.Pane;
import javafx.scene.image.*;

public abstract class SceneObject {
    private Pane pane;
    private Pane bGround;
    private Image icon;
    private int id;
    private int[] location;
    private String typeOfVehicleClass;

    public SceneObject(Pane bGround, int id, int[] location, Image icon) {
        this.pane = new Pane();
        this.bGround = bGround;
        this.icon = icon;
        this.id = id;
        this.location = location;
    }

    public Pane getPane() {
        return pane;
    }
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Pane getBGround() {
        return bGround;
    }

    public Image getIcon() {
        return icon;
    }
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public int[] getLocation() {
        return location;
    }
    public void setLocation(int[] location) {
        this.location = location;
    }

    public String getTypeOfVehicleClass() {
        return typeOfVehicleClass;
    }
    public void setTypeOfVehicleClass(String typeOfVehicleClass) {
        this.typeOfVehicleClass = typeOfVehicleClass;
    }
}