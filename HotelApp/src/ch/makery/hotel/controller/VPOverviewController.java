package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Arrays;

public class VPOverviewController {

    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> nombreColumn;
    @FXML
    private TableColumn<Cliente, String> apellidoColumn;

    @FXML
    private Label dniLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;

    //Reference to the Main
    private Main main;

    /**
     * Constructor
     */
    public VPOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Cliente Table with the two columns.
        nombreColumn.setCellValueFactory(c -> c.getValue().nombreProperty());
        apellidoColumn.setCellValueFactory(c -> c.getValue().apellidosProperty());

        // Clear cliente detail.
        mostrarClienteDetalle(null);

        // Listen for selection changes and show the cliente details when changed.
        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarClienteDetalle(newValue)
        );
    }

    /**
     * Fills all text fields to show details about the Cliente.
     * If the specified cliente is null, all text fields are cleared.
     *
     * @param cliente a Cliente or Null
     */
    private void mostrarClienteDetalle(Cliente cliente) {
        if(cliente != null) {
            // Fill the labels with info from the cliente object.
            dniLabel.setText(cliente.getDni());
            nombreLabel.setText(cliente.getNombre());
            apellidoLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            localidadLabel.setText(cliente.getLocalidad());
            provinciaLabel.setText(cliente.getProvincia());
        } else {
            // cliente is null, remove all the text.
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidoLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }
    public void setMain(Main main) {
        this.main = main;
        // Add obsevable list data to the table
        clienteTable.setItems(main.getClienteData());

        // Order by apellido.
        apellidoColumn.setSortType(TableColumn.SortType.ASCENDING);
        clienteTable.getSortOrder().addAll(apellidoColumn);
    }
}
