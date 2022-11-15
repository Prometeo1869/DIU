package ch.makery.hotel;

import ch.makery.hotel.controller.ClienteEditController;
import ch.makery.hotel.controller.VPOverviewController;
import ch.makery.hotel.model.Cliente;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * The data as an observable list of Clientes.
     */
    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

    public Main() {
        clienteData.add(new Cliente("11555444F", "Silvia", "Zafra Abad", "Calle Parra, 1 1º B", "La Carlota", "Córdoba"));
        clienteData.add(new Cliente("28821684Q", "Juan", "Cebrián Pareja", "Calle Urquiza, 6 4º B", "Sevilla", "Sevilla"));
        clienteData.add(new Cliente("88555222H", "Manuel", "Gonzalez Luque", "Calle Boquerón, 34 1º A", "Fuengirola", "Málaga"));
        clienteData.add(new Cliente("99888777J", "Miguel", "Alarcón García", "Av. Andalucia, 47 2º F", "Écija", "Sevilla"));
    }
    /**
     * Returns the data as an observable list of Clientes.
     * @return
     */
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");

        initRootLayout();

        showVPOverview();
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

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
    public void showVPOverview() {
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
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
            ClienteEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(cliente);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
