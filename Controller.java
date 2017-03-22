package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends GameOfLife implements Initializable{

    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Slider speedModifier;
    @FXML private Slider gameZoom;
    private double cellSize = 10;
    private Color dynamicColor;

    byte[][] testBrett = {
            { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0},
            { 1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        byte[][] testBrett = {
                { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0},
                { 1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        };
        setCanvas(canvas);
        setBoard(testBrett, this);
        draw();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw() {

        if(getBoard() != null) {
            canvas.setHeight(getBoard().length * cellSize);
            canvas.setWidth(getBoard()[0].length * cellSize);

            if (canvas != null) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(dynamicColor);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                for (int i = 0; i < getBoard().length; i++) {
                    for (int j = 0; j < getBoard()[0].length; j++) {
                        if (getBoard()[i][j] == 1) {
                            gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                        }
                    }
                }
            }
        }
    }

    public void clearBoard(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        deleteBoard();
    }

    public void clearEvent(ActionEvent actionEvent) {
        clearBoard();
    }

    public void playGame(ActionEvent actionEvent) {
        getTimeline().play();
    }

    public void nextEvent(ActionEvent actionEvent){
        nextGeneration();
        draw();
    }

    public void pause(ActionEvent actionEvent) {
        getTimeline().stop();
    }

    public void changeColor(ActionEvent actionEvent) {
        this.dynamicColor = colorPicker.getValue();
        draw();
    }

    public void speedMod(MouseEvent mouseEvent) {
        getTimeline().setRate(speedModifier.getValue()/15);
    }

    public void zoomScale(MouseEvent mouseEvent) {
        this.cellSize = gameZoom.getValue();
        draw();
    }

    public void drawCustomBord(ActionEvent actionEvent) {

    }
}

