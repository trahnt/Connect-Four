package connectfour.gui;

import connectfour.model.ConnectFourBoard;
import connectfour.model.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.EnumMap;

/**
 * A JavaFX GUI for the Connect Four game.
 *
 * @author RIT CS
 * @author Trent Wesley
 */
public class ConnectFourGUI extends Application implements Observer<ConnectFourBoard> {
    private Image empty = new Image(getClass().getResourceAsStream("empty.png"));
    private Image black = new Image(getClass().getResourceAsStream("Harambe.png"));
    private Image red = new Image(getClass().getResourceAsStream("knuckles.png"));

    @Override
    public void init() {
        // TODO
    }

    /**
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the GUI
     * @throws Exception if there is a problem
     */
    public void start( Stage stage ) throws Exception {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = makeGridPane();
        borderPane.setCenter(gridPane);
        Label label = new Label("Black turn");
        borderPane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        label = new Label("Moves made: 0\t\t\t\tStatus: NOT_OVER");
        borderPane.setBottom(label);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("ConnectFourGUI");
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Called by the model, model.ConnectFourBoard, whenever there is a state change
     * that needs to be updated by the GUI.
     *
     * @param connectFourBoard the board
     */
    @Override
    public void update(ConnectFourBoard connectFourBoard) {
        // TODO
    }

    /**
     * The main method expects the host and port.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    private GridPane makeGridPane(){
        GridPane gridPane = new GridPane();
        for (int r = 0; r < 7; r++){
            for (int c = 0; c < 6; c++){
                Button button = new Button();
                button.setGraphic(new ImageView(empty));
                gridPane.add(button, c, r);}}
        return gridPane;
    }
}
