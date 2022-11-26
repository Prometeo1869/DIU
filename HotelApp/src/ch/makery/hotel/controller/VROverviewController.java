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
import javafx.scene.control.*;
import java.time.LocalDate;

/**
 * @Autor Juan Cebrian
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
    private DatePicker fechaLlegada, fechaSalida;
    @FXML
    private Spinner<Integer> spNumHab;
    @FXML
    private ChoiceBox<String> menuTipo;
    @FXML
    private Label txtFumador;
    @FXML
    private CheckBox checkFumador;
    @FXML
    private RadioButton rbDesayuno, rbMedia, rbCompleta;
    @FXML
    private Button btLimpiar, btAceptar, btCancelar, btCrear, btModificar, btEliminar;
    private ToggleGroup grupoRegimen;
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

        // Define possible values of ChoiceBox menuTipo
        String[] tipo = new String[4];
        for(int i = 0; i < tipo.length; i++) {
            tipo[i] = Tipo.values()[i].getTexto();
        }
        menuTipo.getItems().addAll(tipo);

        // Define ButtonGroup grupoRegimen to RadioButton Regimen
        grupoRegimen = new ToggleGroup();
        rbDesayuno.setToggleGroup(grupoRegimen);
        rbMedia.setToggleGroup(grupoRegimen);
        rbCompleta.setToggleGroup(grupoRegimen);

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
            fechaLlegada.setValue(reserva.getFechaLlegada());
            fechaLlegada.setEditable(false);
            fechaSalida.setValue(reserva.getFechaSalida());
            fechaSalida.setEditable(false);
            spNumHab.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 1)
            );
            spNumHab.setEditable(false);
            menuTipo.setValue(Tipo.valueOf(String.valueOf(reserva.getTipo())).getTexto());
            menuTipo.setOnAction(e -> {
                menuTipo.setValue(Tipo.valueOf(String.valueOf(reserva.getTipo())).getTexto());
            });
            checkFumador.setSelected(reserva.isFumador());
            checkFumador.setOnAction(e -> {
                checkFumador.setSelected(reserva.isFumador());
                    }
            );
            if (checkFumador.isSelected()) {
                txtFumador.setVisible(true);
            } else {
                txtFumador.setVisible(false);
            }
            opcionRegimen(reserva);
            rbDesayuno.setOnAction(e -> {
                opcionRegimen(reserva);
            });rbMedia.setOnAction(e -> {
                opcionRegimen(reserva);
            });rbCompleta.setOnAction(e -> {
                opcionRegimen(reserva);
            });
        }
    }

    private void opcionRegimen(Reserva reserva) {
        String alojamiento = reserva.getAlojamiento().getTexto();
        switch (alojamiento) {
            case "Alojamiento y desayuno":
                rbDesayuno.setSelected(true);
                break;
            case "Media pensión":
                rbMedia.setSelected(true);
                break;
            case "Pensión completa":
                rbCompleta.setSelected(true);
                break;
        }
    }
    @FXML
    private void pulsarLimpiarReserva() {
        fechaLlegada.setValue(null);
        fechaLlegada.setEditable(true);
        fechaSalida.setValue(null);
        fechaSalida.setEditable(true);
        spNumHab.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0)
        );
        menuTipo.setValue(null);
        checkFumador.setSelected(false);
        txtFumador.setVisible(false);
        rbDesayuno.setSelected(false);
        rbMedia.setSelected(false);
        rbCompleta.setSelected(false);
        btLimpiar.setVisible(true);
        btAceptar.setVisible(true);
        btCancelar.setVisible(true);
        System.out.println(modelo.getRep().totalReservas());
    }
    @FXML
    private void pulsarCrearReserva() {
        pulsarLimpiarReserva();
    }
    public void guardarReservaNueva() throws ExceptionReserva {
        Reserva tempReserva = new Reserva();
        if(isInputValid()) {
            tempReserva.setCodigo(modelo.getRep().totalReservas() + 1);
            tempReserva.setCliente(dniTxt.getText());
            tempReserva.setFechaLlegada(fechaLlegada.getValue());
            tempReserva.setFechaSalida(fechaSalida.getValue());
            tempReserva.setTipo(Tipo.getTipoByTexto(menuTipo.getValue()));
            tempReserva.setFumador(checkFumador.isSelected());
            if(grupoRegimen.getSelectedToggle() == rbDesayuno) {
                tempReserva.setAlojamiento(Regimen.DESAYUNO);
            } else if(grupoRegimen.getSelectedToggle() == rbMedia) {
                tempReserva.setAlojamiento(Regimen.MEDIA);
            } else {
                tempReserva.setAlojamiento(Regimen.COMPLETA);
            }

            okClicked = true;
        }
        if(okClicked) {
            main.getReservaData().add(tempReserva);
            // AÑADE A LA BASE DE DATOS
            modelo.getRep().addReserva(tempReserva);
        }
    }


    private boolean isInputValid() {
        String errorMessage = "";
        //Si las fechas están vacias o la fecha de salida es anterior a la de llegada
        if(fechaLlegada.getValue() != null && fechaSalida.getValue() != null) {
            if(fechaLlegada.getValue().compareTo(fechaSalida.getValue()) > 0) {
                errorMessage += "Fecha de salida anterior a fecha de llegada\n";
            }
        }
        if(fechaLlegada.getValue() == null) {
            errorMessage += "Introduzca la fecha de llegada\n";
        }
        if(fechaSalida.getValue() == null) {
            errorMessage += "Introduzca la fecha de salida\n";
        }
        // Si el número de habitaciones es 0
        if(spNumHab.getValue() == 0) {
            errorMessage += "Número de habitaciones: 0\n";
        }
        // Si no se ha seleccionado el tipo de habitación
        if(menuTipo.getValue() == null) {
            errorMessage += "Seleccione el tipo de habitación\n";
        }
        // Si no se selecciona el regimen de alojameinto
        if(rbDesayuno.isSelected() == false && rbMedia.isSelected() == false && rbCompleta.isSelected() == false) {
            errorMessage += "Seleccione el régimen de alojamiento\n";
        }
        // Validar o mensaje de advertencia
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos no válidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
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
    }

}
