package Vehicle;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.image.Image;
import java.util.List;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

abstract class Vehicle extends SceneObject {
    private int[] curId = {0,-1};
    private int [] route;
    private int maxSpeed;
    private boolean running=true;
    private CopyOnWriteArrayList<int []> allLocationCoordinates;
    private List<Port> ports;

    private Label textLabel = new Label();
    private Pane paneOpt = new Pane();
    private Stage stageOpt = new Stage();
    public Vehicle(Pane bGround, int id, int[] location, Image icon, int maxSpeed, List<Port> ports, int [] route,
                   CopyOnWriteArrayList<int []> allLocationCoordinates){
        super(bGround, id, location, icon);
        this.route = route;
        this.maxSpeed = maxSpeed;
        this.allLocationCoordinates = allLocationCoordinates;
        this.allLocationCoordinates.add(new int[] {id, getLocation()[0], getLocation()[1], curId[0], curId[1]});
        this.ports = ports;

        ImageView imageView = new ImageView(icon);
        getPane().setLayoutX(location[0]);
        getPane().setLayoutY(location[1]);
        getPane().getChildren().add(imageView);
        bGround.getChildren().add(getPane());

        stageOpt.setScene(new Scene(paneOpt, 460, 100));
            Button removeVehicle = new Button("Remove from map");
            removeVehicle.setLayoutX(270);
            removeVehicle.setLayoutY(15);
            removeVehicle.setOnAction(event3 -> {
                setRunning(false);
                stageOpt.close();
        });
            paneOpt.getChildren().add(removeVehicle);
    }

    public int[] getCurId() {
        return curId;
    }
    public void setCurId(int[] curId) {
        this.curId = curId;
    }

    public int[] getRoute() {
        return route;
    }
    public void setRoute(int[] route) {
        this.route = route;
    }

    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<Port> getPlaces() {
        return ports;
    }

    public double whatIsTheDistance(int x, int x1, int y, int y1){
        return Math.sqrt((x1 - x)*(x1 - x)+(y1-y)*(y1-y));
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public synchronized void goAhead(){
        if(isRunning()) {
            int[] arr = findPath(curId[0]);

            setLocation(new int[]{getLocation()[0] + arr[0], getLocation()[1] + arr[1]});
            getPane().relocate(getLocation()[0], getLocation()[1]);

            allLocationCoordinates.get(findLocation(getId()))[1] = getLocation()[0];
            allLocationCoordinates.get(findLocation(getId()))[2] = getLocation()[1];
            allLocationCoordinates.get(findLocation(getId()))[3] = curId[0];
            allLocationCoordinates.get(findLocation(getId()))[4] = curId[1];
        }
    }

    public List<Port> getports() {
        return ports;
    }

    public Port findVehicle(int id){
        for (Port airport : ports) {
            if(id == airport.getId()) {
                return airport;
            }
        }
        return null;
    }

    public int findLocation(int id){
        for(int i = 0; i< allLocationCoordinates.size(); i++){
            if(id== allLocationCoordinates.get(i)[0])
                return i;
        }
        return -1;
    }

    public String routeText(){
        String route = findVehicle(getRoute()[0]).getPortName();
        for(int i=1; i<getRoute().length; i++){
            if(getRoute()[i]<10)
                route+="->"+ findVehicle(getRoute()[i]).getPortName();
        }
        return route;
    }
    public Pane getPaneOpt() {
        return paneOpt;
    }
    public Stage getStageOpt() {
        return stageOpt;
    }

    public int[] findPath(int id){
        int XDestination = findVehicle(id).getLocation()[0];
        int YDestination = findVehicle(id).getLocation()[1];
        double deltaX = XDestination - getLocation()[0];
        double deltaY = YDestination - getLocation()[1];
        double angle = Math.atan2( deltaY, deltaX );
        int alfa = (int)(getMaxSpeed()*Math.cos( angle ));
        int beta = (int)(getMaxSpeed()*Math.sin( angle ));
        return new int [] {alfa,beta};
    }
}

