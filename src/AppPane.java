import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppPane {
    private Pane pane = new Pane();

    public AppPane() {
        Stage optionsWin = new Stage();
        optionsWin.setTitle("Options");
        optionsWin.setScene(new Scene(pane, 290, 70));
        optionsWin.show();

        optionsWin.setOnCloseRequest(we -> {
            optionsWin.close();
            System.exit(0);
        });
    }

    public Pane getPane() {
        return pane;
    }
    public void setPane(Pane control) {
        pane = control;
    }
}
