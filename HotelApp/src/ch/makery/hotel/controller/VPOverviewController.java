package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    @FXML
    private void pulsarEliminarCliente() {
        int posicion = clienteTable.getSelectionModel().getSelectedIndex();
        if(posicion >= 0) {
            clienteTable.getItems().remove(posicion);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ningún Cliente Seleccionado");
            alert.setContentText("Por favor, seleccine algún cliente para borrarlo");
            alert.showAndWait();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new cliente.
     */
    @FXML
    private void pulsarCrearCliente() {
        Cliente tempCliente = new Cliente();
        boolean okClicked = main.mostrarClienteEditDialog(tempCliente);
        if (okClicked) {
            main.getClienteData().add(tempCliente);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void pulsarModificarCliente() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = main.mostrarClienteEditDialog(selectedCliente);
            if (okClicked) {
                mostrarClienteDetalle(selectedCliente);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ningún Cliente Seleccionado");
            alert.setContentText("Por favor, seleccine algún cliente para editarlo");
            alert.showAndWait();
        }
    }
    public void setMain(Main main) {
        this.main = main;
        // Add obsevable list data to the table
        clienteTable.setItems(main.getClienteData());

        ordenar();
    }

    public void ordenar() {
        // Order by apellido.
        apellidoColumn.setSortType(TableColumn.SortType.ASCENDING);
        clienteTable.getSortOrder().addAll(apellidoColumn);
    }
}
