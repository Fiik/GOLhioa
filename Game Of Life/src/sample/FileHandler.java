package sample;

import javafx.stage.FileChooser;

/**
 * Created by Fikre on 27.03.2017.
 */
public class FileHandler {

    public static void openFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open new game");
        fileChooser.showOpenDialog(null);

        //Fil-filter:
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text-files", "*.txt"),
                new FileChooser.ExtensionFilter("RLE", "*.rle")
        );
    }
}
