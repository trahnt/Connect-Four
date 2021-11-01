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
import connectfour.model.ConnectFourBoard.Player;

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
    private BorderPane borderPane;
    private GridPane gridPane;
    private ConnectFourBoard board;

    public final static int ROWS = 6;
    public final static int COLS = 7;

    @Override
    public void init() {
        this.board = new ConnectFourBoard();
        board.addObserver(this);
    }

    /**
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the GUI
     * @throws Exception if there is a problem
     */
    public void start( Stage stage ) throws Exception {
        borderPane = new BorderPane();
        gridPane = makeGridPane();
        borderPane.setCenter(gridPane);
        Label label = new Label("Current Player: P1");
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

        Label label = new Label("Current Player: " + board.getCurrentPlayer());
        borderPane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        borderPane.setBottom(new Label("Moves Made: " + board.getMovesMade() + "\t\t\t\tStatus: " +
                board.getGameStatus()));
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
        for (int c = 0; c < COLS; c++){
            for (int r = 0; r < ROWS; r++){
                Connect4Button button = new Connect4Button(Player.NONE, r, c);
                gridPane.add(button, c, r);
            }}
        return gridPane;
    }

    private void buttonPressed(Connect4Button button){
        if (this.board.getGameStatus() == ConnectFourBoard.Status.NOT_OVER){
            Player p = board.getCurrentPlayer();
            if (board.isValidMove(button.column)){

                for (int row=ROWS-1; row >= 0; --row) {
                    if (board.getContents(row, button.column) == Player.NONE) {
                        gridPane.add(new Connect4Button(p, row, button.column), button.column, row);
                        break;}}

                board.makeMove(button.column);
                update(board);
            }}
    }

    private class Connect4Button extends Button{
        private int row;
        private int column;
        private Player p;

        public Connect4Button(Player p, int row, int column){
            this.row = row;
            this.column = column;
            this.p = p;
            Image image = switch (p) {
                case P1 -> black;
                case P2 -> red;
                default -> empty;
            };
            this.setOnAction(event -> buttonPressed(this));
            this.setGraphic(new ImageView(image));}
    }
}
