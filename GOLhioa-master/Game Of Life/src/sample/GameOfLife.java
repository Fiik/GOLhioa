package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


/**
 * Created by Fikre on 07.03.2017.
 */
public class GameOfLife {

    public Rules RuleType = new ConwaysRules();
    private byte[][] board;
    private Timeline timeline = new Timeline();
    private Controller context;


    // GraphicsContext gc = canvas.getGraphicsContext2D(); Hvis den ligger ute kan den nas av alle metoder men ligger på minne hele tiden, best utenfor eller inni metodene?

    public void setBoard(byte[][] board, Controller context){
        this.board = board;
        this.context = context;

        //JAVA ANIMASJON
        KeyFrame frame = new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextGeneration();
                context.draw();
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void nextGeneration(){
        if(board != null){
            byte[][] nyttBrett = new byte[board.length][board[0].length];

            for(int y=0; y < board.length; y++){
                for (int x=0; x < board[0].length; x++){
                    nyttBrett[y][x] = (byte) RuleType.overlever(board[y][x], countNeighbours(y, x));
                }
            }
            this.board = nyttBrett;
        }
    }

    private int countNeighbours(int y, int x) {

        int livingNeighbours = 0;
            for (int i = -1; i <= 1; i++) {
                for (int k = -1; k <= 1; k++) {
                    if (k == 0 && i == 0) { // Remove self testing
                        continue;
                    }
                    try { // Catch outside borders
                        if (board[y + i][x + k] == 1) { // If cell state alive
                            livingNeighbours++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {//Outside borders}
                    }
                }
            }
        return livingNeighbours;
    }

    public byte[][] getBoard(){
        return board;
    }

    public Timeline getTimeline(){
        return timeline;
    }

    public void deleteBoard(){
        board = null;
    }
}
