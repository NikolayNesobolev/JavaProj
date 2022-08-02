package Vehicle;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.layout.Pane;

public abstract class Plane extends Vehicle implements Runnable {
    private double fuel;
    private final int numOfEmployees;
    private boolean ChangeRouteCheck = false;
    private boolean leaveTheAirport = false;
    private boolean dangerReport = false;
    private int timerCount=15;

    public Plane(Pane bGround, int id, int[] location, Image icon, int numOfEmployees, double fuel, int maxSpeed,
                 List<Port> ports, int[] route, CopyOnWriteArrayList<int[]> allLocationCoordinates) {
        super(bGround, id, location, icon, maxSpeed, ports, route, allLocationCoordinates);

        this.fuel = fuel;
        this.numOfEmployees = numOfEmployees;

        Button changeRoute = new Button("Request a change of the route");
        changeRoute.setLayoutX(270);
        changeRoute.setLayoutY(40);
        changeRoute.setOnAction(event3 -> {
            ChangeRouteCheck = true;
            getStageOpt().close();
        });
        getPaneOpt().getChildren().add(changeRoute);

            Button emergencyLanding = new Button("Request an emergency landing");
        emergencyLanding.setLayoutX(270);
        emergencyLanding.setLayoutY(65);
        emergencyLanding.setOnAction(event2 -> {
            dangerReport = true;
            getStageOpt().close();
        });
        getPaneOpt().getChildren().add(emergencyLanding);

        getPaneOpt().getChildren().add(getTextLabel());
    }

    public double getFuel() {
        return fuel;
    }
    public boolean isLeaveTheAirport() {
        return leaveTheAirport;
    }
    public void setLeaveTheAirport(boolean leaveTheAirport) {
        this.leaveTheAirport = leaveTheAirport;
    }

    int findClose() {
        double buffor;
        double distance = 99999;
        int id = 0;
        for (Port port : getports()) {
            buffor = whatIsTheDistance(getLocation()[0], getports().get(port.getId() - 1).getLocation()[0], getLocation()[1], getports().get(port.getId() - 1).getLocation()[1]);
            if (buffor < distance) {
                distance = buffor;
                id = port.getId();
            }
        }
        return id;
    }

    public synchronized void run() {
        AnimationTimer timer = new AnimationTimer() {
            long lastTick = 0;
            int routeIndex = 0;

            public void handle(long actual) {
                if (lastTick == 0) {
                    lastTick = actual;
                    return;
                }
                if (actual - lastTick > 100000000) {
                    lastTick = actual;
                    if (routeIndex >= getRoute().length) routeIndex = 0;
                    if (!isRunning()) {
                        getBGround().getChildren().remove(getPane());
                        this.stop(); }
                    if (dangerReport) getCurId()[0] = findClose();
                    else getCurId()[0] = getRoute()[routeIndex];
                    getTextLabel().setText("Id: " + getId() + ";\t   Number of employees: " + numOfEmployees + ";\n" + routeText()
                            + "\nMax speed: " + getMaxSpeed() + ";\t   Fuel: " + getFuel()
                            + ";\nLocation: [" + getLocation()[0] + ", " + getLocation()[1] +"]" + ";\t   Next station: "
                            + findVehicle(getCurId()[0]).getPortName() + ";");

                    if (whatIsTheDistance(getLocation()[0] + findPath(getCurId()[0])[0],
                            findVehicle(getCurId()[0]).getLocation()[0],
                            getLocation()[1] + findPath(getCurId()[0])[1],
                            findVehicle(getCurId()[0]).getLocation()[1]) > getMaxSpeed() * Math.sqrt(2)){
                        goAhead();
                        fuel--;
                    }
                    else if (!findVehicle(getCurId()[0]).isTaken()){
                        goAhead();
                        fuel--;
                    }
                    if (whatIsTheDistance(getLocation()[0], findVehicle(getCurId()[0]).getLocation()[0], getLocation()[1],
                            findVehicle(getCurId()[0]).getLocation()[1]) <= getMaxSpeed() * Math.sqrt(2)) {
                        getCurId()[1] = getCurId()[0];
                        findVehicle(getCurId()[0]).setTaken(true);
                        if (dangerReport) setRunning(false);
                        if(timerCount <1){
                            if (!dangerReport) routeIndex++;
                            else dangerReport = false;
                            leaveTheAirport =true;
                            timerCount =20;
                            fuel=200;
                            findVehicle(getCurId()[0]).setTaken(false);
                        }
                        else timerCount--;
                    }
                    if (ChangeRouteCheck){
                        int selectedNewRandomRoute = ThreadLocalRandom.current().nextInt(7);
                        int []changedRoute = new int[selectedNewRandomRoute];
                        changedRoute[0] = getports().get(ThreadLocalRandom.current().nextInt(2,getPlaces().size())).getId();
                        for(int i=1; i<selectedNewRandomRoute; i++) {
                            changedRoute[i] = getports().get(ThreadLocalRandom.current().nextInt(2,getPlaces().size())).getId();
                            while (changedRoute[i] == changedRoute[i - 1])
                                changedRoute[i] = getports().get(ThreadLocalRandom.current().nextInt(2,getPlaces().size())).getId();
                        }
                        ChangeRouteCheck =false;
                        setRoute(changedRoute);
                        setCurId(new int[]{0,0});
                    }
                }
            }
        };
        timer.start();
    }
}
