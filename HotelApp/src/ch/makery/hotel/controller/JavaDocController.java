package ch.makery.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * @author Juan Cebrián
 */
public class JavaDocController {

    @FXML
    private WebView webView;
    private Stage dialogStage;

    @FXML
    private void initialize() {

        webView.getEngine().load(Paths.get("../javadoc/index.html").toUri().toString());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
