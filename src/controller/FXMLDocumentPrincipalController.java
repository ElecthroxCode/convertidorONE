package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FXMLDocumentPrincipalController implements Initializable {

    @FXML
    private ImageView imgTemp;

    @FXML
    private ImageView imgDivisa;

    Stage stage = new Stage();

    @FXML
    void mouseEventClickDivisa(MouseEvent event) {
        String panelTextDivLabel = "Convertidor de divisas";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLpanel.fxml"));
        try {
            Parent root = loader.load();
            FXMLpanelController controllerPanel = loader.getController();
            controllerPanel.toggleTitlePanel(panelTextDivLabel);
            controllerPanel.switchContentPanel(controllerPanel.getPanelTemp(), false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/img/oracle_logo_icon.png"));
            stage.setTitle("Programa ONE");
            stage.show();
            Stage myStage = (Stage) this.imgDivisa.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void mouseEventClickTemp(MouseEvent event) {
        String panelTextTenpLabel = "Convertidor de temperatura";
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLpanel.fxml"));
        try {
            Parent root = loader.load();
            FXMLpanelController controllerPanel = loader.getController();
            controllerPanel.toggleTitlePanel(panelTextTenpLabel);
            controllerPanel.switchContentPanel(controllerPanel.getPanelDivisas(), false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/img/oracle_logo_icon.png"));
            stage.setTitle("Programa ONE");
            stage.show();
            Stage myStage = (Stage) this.imgTemp.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
