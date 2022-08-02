package Vehicle;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Port extends SceneObject {
    private boolean taken=false;
    private String portName;
    public Port(Pane bGround, int id, int[] location, Image icon, String portName, Boolean visibility) {
        super(bGround, id, location, icon);

        boolean visible = visibility;

        if(visible){
            ImageView imView = new ImageView(icon);
            this.portName = portName;
            Pane pane = new Pane();
            pane.setLayoutX(location[0]);
            pane.setLayoutY(location[1]);
            pane.getChildren().add(imView);
            bGround.getChildren().add(pane);
        }
    }

    public String getPortName() {
        return portName;
    }
    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

}
