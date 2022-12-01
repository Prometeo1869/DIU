package ch.makery.hotel;

import ch.makery.hotel.controller.*;
import ch.makery.hotel.model.*;
import ch.makery.hotel.model.repository.impl.ClienteRepositoryImpl;
import ch.makery.hotel.model.repository.impl.ReservasRepositoryImp;
import ch.makery.hotel.util.Convert;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


/**
 * @author Juan Cebrian
 */
public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ClienteModelo clienteModelo;
    private ClienteRepositoryImpl clienteRepository;
    private boolean ok = true;
    /**
     * The data as an observable list of Clientes.
     */
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

    private ReservaModelo reservaModelo;
    private ReservasRepositoryImp reservasRepository;
    /**
     * The data as an observable list of Reservas.
     */
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();

    /**
     * @throws ExceptionCliente
     */
    public Main() throws ExceptionCliente {
        try {
            this.clienteModelo = new ClienteModelo();
            this.clienteRepository = new ClienteRepositoryImpl();
            clienteModelo.setRep(this.clienteRepository);
            for (ClienteVO c : clienteModelo.obtenerClientes()) {
                Cliente nuevo = Convert.convertTo(c);
                clienteData.add(nuevo);
            }
        } catch (ExceptionCliente e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR DE CONEXIÓN");
            alert.setContentText("Error al conectar con la base de datos");
            alert.showAndWait();
            ok = false;
        }

        this.reservaModelo = new ReservaModelo();
        this.reservasRepository = new ReservasRepositoryImp();
        reservaModelo.setRep(this.reservasRepository);

    }

    /**
     * Returns the data as an observable list of Clientes.
     *
     * @return
     */
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }

    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");
        if(ok == true) {
            initRootLayout();
            mostrarVPOverview();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            // Set the cliente into the controller.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Shows the principal view inside the root layout.
     */
    public void mostrarVPOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VPOverview.fxml"));
            AnchorPane clienteOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(clienteOverview);

            //Give the controller access to the Main.
            VPOverviewController controller = loader.getController();
            controller.setMain(this);
            controller.setClienteModelo(this.clienteModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified cliente. If the user
     * clicks OK, the changes are saved into the provided cliente object and true
     * is returned.
     *
     * @param cliente the cliente object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean mostrarClienteEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ClienteEditDialog.fxml"));
            AnchorPane editPage = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(editPage);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
            ClienteEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);


            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mostrarReservas(Cliente selectedCliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VROverview.fxml"));
            AnchorPane reservasPage = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Reservas");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(reservasPage);
            dialogStage.setScene(scene);

            //
            reservasRepository.setCliente(selectedCliente);
            reservaData.clear();
            for (ReservaVO r : reservaModelo.obtenerReservas()) {
                Reserva nuevo = Convert.convertTo(r);
                reservaData.add(nuevo);
            }

            // Set the cliente into the controller.
            VROverviewController controller = loader.getController();
            controller.setCliente(selectedCliente);
            controller.mostrarDatosCliente(selectedCliente);
            //Give the controller access to the Main.
            controller.setMain(this);
            controller.setReservaModelo(this.reservaModelo);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ExceptionReserva e) {
            throw new RuntimeException(e);
        }
    }

    public boolean mostrarReservaEditDialog(Reserva reserva) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ReservaEditDialog.fxml"));
            AnchorPane editPage = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(editPage);
            dialogStage.setScene(scene);

            // Set the reserva into the controller.
            ReservaEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReserva(reserva);
            //Give the controller access to ReservaModelo
            controller.setReservaModelo(this.reservaModelo);

            // Show the dialog and wait until the user closes it.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void mostrarJavaDocDialog() {

            try {
                WebView webView = new WebView();

                WebEngine webEngine = webView.getEngine();
                webEngine.load("https://www.google.com/");

                StackPane root = new StackPane();
                root.getChildren().add(webView);

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Ver JavaDoc");
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(root);
                dialogStage.setScene(scene);

                dialogStage.showAndWait();


            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: No puede abrir el WebView");
            }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void mostrarOcupacion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/OcupacionDialog.fxml"));
            AnchorPane editPage = loader.load();

            StackPane root = new StackPane();
            root.getChildren().add(editPage);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estadisticas de Ocupación por mes");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            //Set the reservas into the controller

            OcupacionController controller = loader.getController();
            controller.setReservaData(reservasRepository.listaReservasTotales());

            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: No pueden abrir la ventana de estadísticas");
        }
    }

    public void mostrarTipoHabitacion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TipoHabitacionDialog.fxml"));
            AnchorPane editPage = loader.load();

            StackPane root = new StackPane();
            root.getChildren().add(editPage);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ocupación y tipo de habitaciones");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            //Set the reservas into the controller

            TipoHabitacionController controller = loader.getController();
            controller.calculoProgressBar(reservasRepository.listaReservasTotales());

            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: No pueden abrir la ventana de estadísticas");
        }
    }
}
