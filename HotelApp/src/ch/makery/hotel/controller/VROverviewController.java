package ch.makery.hotel.controller;

import ch.makery.hotel.Main;
import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.ExceptionReserva;
import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.model.ReservaModelo;
import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * @author Juan Cebrián
 */
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
    private TextField nombreTxt, apellidosTxt, direccionTxt, localidadTxt, provinciaTxt;
    @FXML
    private Button btCrear, btModificar, btEliminar;
    @FXML
    private Label labelFechaLlegada, labelFechaSalida, labelNumHab, labelTipo, labelFumador, labelRegimen;

    //Reference to the Main
    private Main main;
    private ReservaModelo modelo;
    //Reference to the selected Cliente
    private Cliente cliente;
    private boolean okClicked = false;

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
        mostrarReservaDetalle(null);

        // Listen for selection changes and show the reserva details when changed
        reservaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarReservaDetalle(newValue)
        );
    }
    public void mostrarDatosCliente(Cliente cliente) {
        // Fill the labels with info from the cliente object.
            dniTxt.setText(cliente.getDni());
            dniTxt.setEditable(false);
            nombreTxt.setText(cliente.getNombre());
            nombreTxt.setEditable(false);
            apellidosTxt.setText(cliente.getApellidos());
            apellidosTxt.setEditable(false);
            direccionTxt.setText(cliente.getDireccion());
            direccionTxt.setEditable(false);
            localidadTxt.setText(cliente.getLocalidad());
            localidadTxt.setEditable(false);
            provinciaTxt.setText(cliente.getProvincia());
            provinciaTxt.setEditable(false);
    }
    private void mostrarReservaDetalle(Reserva reserva) {
        if(reserva != null) {
            labelFechaLlegada.setText(reserva.getFechaLlegada().toString());
            labelFechaSalida.setText(reserva.getFechaSalida().toString());
            labelNumHab.setText("1");
            labelTipo.setText(reserva.getTipo().getTexto());
            labelFumador.setText(reserva.isFumador() ? "Si" : "No");
            labelRegimen.setText(reserva.getAlojamiento().getTexto());
            btModificar.setVisible(true);
            btEliminar.setVisible(true);
        } else {
            labelFechaLlegada.setText("");
            labelFechaSalida.setText("");
            labelNumHab.setText("");
            labelTipo.setText("");
            labelFumador.setText("");
            labelRegimen.setText("");
        }
    }
    @FXML
    private void pulsarCrearReserva() throws ExceptionReserva {
        Reserva tempReserva = new Reserva();
        tempReserva.setCliente(cliente.getDni()); // Asignar Cliente a la nueva reserva
        tempReserva.setCodigo(modelo.getRep().elegirCodigoLibre()); //Asignar codigo a la nueva reserva
        boolean okClicked = main.mostrarReservaEditDialog(tempReserva);
        if (okClicked) {
            main.getReservaData().add(tempReserva);
            // AÑADE A LA BASE DE DATOS
            modelo.getRep().addReserva(tempReserva);
            this.ordenarReservas();
        }
    }
    @FXML
    private void pulsarModificarReserva() throws ExceptionReserva {
        Reserva selectedReserva = reservaTable.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = main.mostrarReservaEditDialog(selectedReserva);
            if (okClicked) {
                mostrarReservaDetalle(selectedReserva);
                modelo.getRep().editReserva(selectedReserva);
                this.ordenarReservas();
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ninguna Reserva Seleccionada");
            alert.setContentText("Por favor, seleccine alguna reserva para editarla");
            alert.showAndWait();
        }
    }
    public void pulsarEliminar() throws ExceptionReserva{
        int posicion = reservaTable.getSelectionModel().getSelectedIndex();
        int code = reservaTable.getItems().get(posicion).getCodigo();
        if(posicion >= 0) {
            //BORRA DE LA APP
            reservaTable.getItems().remove(posicion);
            //BORRA DE LA BASE DE DATOS
            modelo.getRep().deleteReserva(code);
        }
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
        reservaTable.setItems(main.getReservaData());

        ordenarReservas();
    }

    private void ordenarReservas() {
        // Order by fechaLlegada.
        fechaColumn.setSortType(TableColumn.SortType.ASCENDING);
        reservaTable.getSortOrder().addAll(fechaColumn);
    }

    public void volverACliente(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
