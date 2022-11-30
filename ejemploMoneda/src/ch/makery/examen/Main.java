package ch.makery.examen;

import Modelo.ExcepcionMoneda;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import ch.makery.examen.controller.RootLayoutController;
import ch.makery.examen.controller.VentanaPrincipalController;
import ch.makery.examen.controller.VentanaSecundariaController;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import ch.makery.examen.conexionCasa.MonedaRepo;
import ch.makery.examen.util.Convert;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Modelo.MonedaVO;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private MonedaModelo monedaModelo;
    private MonedaRepository monedaRepository; //////////////////////////////////////////////////////////////
    private boolean ok = true;
    private ObservableList<Moneda> monedaData = FXCollections.observableArrayList();

    public Main() {
        try {
            this.monedaModelo = new MonedaModelo();
            this.monedaRepository = new MonedaRepositoryImpl(); ///////////////////////////////////////////////
            monedaModelo.setRepos(this.monedaRepository);

            for (MonedaVO mvo : monedaModelo.obtenerListaMonedas()) {
                Moneda m = Convert.convertTo(mvo);
                monedaData.add(m);
            }
        } catch (ExcepcionMoneda e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR DE CONEXIÓN");
            alert.setContentText("Error al conectar con la base de datos");
            alert.showAndWait();
        }
    }
    @Override
    public void start(Stage primaryStage) throws ExcepcionMoneda {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Examen DIU");
        initRootLayout();
        cargarVentanaPrincipal();
    }
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            RootLayoutController rlController = loader.getController();
            rlController.setMain(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void cargarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VentanaPrincipal.fxml"));
            AnchorPane ventanaPrincipal = loader.load();

            rootLayout.setCenter(ventanaPrincipal);

            VentanaPrincipalController vpController = loader.getController();
            vpController.setMain(this);
            vpController.setMonedaModelo(this.monedaModelo);
            vpController.setMonedaData(monedaData);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void abrirVentanaNueva() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VentanaSecundaria.fxml"));
            AnchorPane editPage = loader.load();

            StackPane root = new StackPane();
            root.getChildren().add(editPage);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ventana Secundaria");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            VentanaSecundariaController vsController = loader.getController();
            vsController.setMonedaData(monedaRepository.ObtenerListaMonedas());

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No se abre la ventana secundaria");
            alert.showAndWait();
        } catch (ExcepcionMoneda e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Moneda> getMonedaData() {
        return this.monedaData;
    }
}
