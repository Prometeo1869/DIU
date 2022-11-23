package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;

public class VROverviewController {

    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TableColumn<Reserva, Integer> codigoColumn;
    @FXML
    private TableColumn<Reserva, Date> fechaColumn;
    @FXML
    private TextField dniTxt;
    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField apellidosTxt;
    @FXML
    private TextField direccionTxt;
    @FXML
    private TextField localidadTxt;
    @FXML
    private TextField provinciaTxt;

    //Reference to the Main
    private Main main;
    //Reference to the selected Cliente
    private Cliente cliente;

    /**
     * Constructor
     */
    public VROverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the reservaTable with the two Columns
        //codigoColumn.setCellFactory(c -> c.getValue());
    }

    public void mostrarDatosCliente(Cliente cliente) {
        // Fill the labels with info from the cliente object.

            dniTxt.setText(cliente.getDni());
            nombreTxt.setText(cliente.getNombre());
            apellidosTxt.setText(cliente.getApellidos());
            direccionTxt.setText(cliente.getDireccion());
            localidadTxt.setText(cliente.getLocalidad());
            provinciaTxt.setText(cliente.getProvincia());

    }

    public void setCliente(Cliente selectedCliente) {
        this.cliente = selectedCliente;
    }



    public boolean isOkClicked() {
        return true;
    }
}
