package Core;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;

public class Map {
    private Pane pane = new Pane();

    public Map(Image mapBGround){
        Stage MapScene = new Stage();
        MapScene.setScene(new Scene(pane, 1200, 715));
        MapScene.setTitle("WrldMap");

        ImageView imageMap = new ImageView(mapBGround);
        pane.getChildren().add(imageMap);
        MapScene.show();

        MapScene.setOnCloseRequest(we -> {
            MapScene.close();
            System.exit(0);
        });
    }

    public Pane getPane() {
        return pane;
    }
    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
