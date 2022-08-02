package Vehicle;
import java.util.List;
import javafx.scene.image.Image;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public abstract class Ship extends Vehicle implements Runnable {
    private final int [] homeRoute;

    public Ship(Pane bGround, int id, int[] location, Image icon, int maxSpeed, List<Port> ports, int [] route,
                CopyOnWriteArrayList<int []> allLocationCoordinates) {
        super(bGround, id, location, icon, maxSpeed, ports, route, allLocationCoordinates);

        getPaneOpt().getChildren().add(getTextLabel());
        homeRoute = new int [getRoute().length-1];
        System.arraycopy(route,1, homeRoute,0,route.length-1);
    }

    public synchronized void run() {
        AnimationTimer timer = new AnimationTimer() {
            int routeIndex = 0;
            long lastTick = 0;

            public void handle(long actual) {
                if (lastTick == 0) {
                    lastTick = actual;
                    return;
                }
                if (actual - lastTick > 999999999 / getMaxSpeed()) {
                    lastTick = actual;
                    if (routeIndex >= homeRoute.length) {
                        routeIndex = 1;
                        goHome();
                    }
                    if(!isRunning())
                    {
                        getBGround().getChildren().remove(getPane());
                        this.stop();
                    }

                    getCurId()[0] = homeRoute[routeIndex];
                    getTextLabel().setText("Id: " + getId() +";\t   " + routeText()
                            + ";\nLocation: ["+ getLocation()[0] +", "+ getLocation()[1] + "]" + ";\t   Max speed: " + getMaxSpeed() + ";");
                    goAhead();

                    if (whatIsTheDistance(getLocation()[0], findVehicle(getCurId()[0]).getLocation()[0], getLocation()[1],
                            findVehicle(getCurId()[0]).getLocation()[1]) <= getMaxSpeed()) {
                        getCurId()[1]= getCurId()[0];
                        routeIndex++;
                    }
                }
            }

            public void goHome(){
                int temporaryInt, l= homeRoute.length;
                for (int i = 0; i < homeRoute.length / 2; i++) {
                    temporaryInt = homeRoute[i];
                    homeRoute[i] = homeRoute[l - i - 1];
                    homeRoute[l - i - 1] = temporaryInt;
                }
            }
        };
        timer.start();
    }
}
