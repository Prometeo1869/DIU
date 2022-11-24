package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.model.ReservaModelo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;

public class VROverviewController {

    @FXML
    private TableView<Reserva> reservaTable;
    @FXML
    private TableColumn<Reserva, Number> codigoColumn;
    @FXML
    private TableColumn<Reserva, LocalDate> fechaColumn;
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
    private ReservaModelo modelo;
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
        codigoColumn.setCellValueFactory(c -> c.getValue().codigoProperty());
        fechaColumn.setCellValueFactory(c -> c.getValue().fechaLlegadaProperty());

        //Clear Reservas datail

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
    public void setReservaModelo(ReservaModelo modelo) {
        this.modelo = modelo;
    }



    public boolean isOkClicked() {
        return true;
    }

    public void setMain(Main main) {
        this.main = main;
        // Add obsevable list data to the table
        ObservableList <Reserva> reservasDeCliente = main.getReservaData();
        for(Reserva r : reservasDeCliente) {
            if(!r.getCliente().equals(this.cliente.getDni())) {
                reservasDeCliente.remove(r);
            }
        }
        reservaTable.setItems(reservasDeCliente);
    }
}
