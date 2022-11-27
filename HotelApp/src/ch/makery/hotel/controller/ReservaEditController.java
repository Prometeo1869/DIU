package ch.makery.hotel.controller;

import ch.makery.hotel.model.ExceptionReserva;
import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.model.ReservaModelo;
import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * @author Juan Cebrian
 */
public class ReservaEditController {

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
    private ToggleGroup grupoRegimen;
    private ReservaModelo modelo;
    private Stage dialogStage;
    //Reference to the selected reserva
    private Reserva reserva;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        // Define possible values of ChoiceBox menuTipo
        String[] tipo = new String[4];
        for(int i = 0; i < tipo.length; i++) {
            tipo[i] = Tipo.values()[i].getTexto();
        }
        menuTipo.getItems().addAll(tipo);
        // Define Spinner Value Factory spNumHab
        spNumHab.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0)
        );
        // Define ButtonGroup grupoRegimen to RadioButton Regimen
        grupoRegimen = new ToggleGroup();
        rbDesayuno.setToggleGroup(grupoRegimen);
        rbMedia.setToggleGroup(grupoRegimen);
        rbCompleta.setToggleGroup(grupoRegimen);
    }
    @FXML
    private void pulsarLimpiarReserva() {

        fechaLlegada.setValue(null);
        fechaSalida.setValue(null);
        spNumHab.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0)
        );
        menuTipo.getSelectionModel().clearSelection();
        checkFumador.setSelected(false);
        checkFumador.isIndeterminate(); //?
        txtFumador.setVisible(false);
        rbDesayuno.setSelected(false);
        rbMedia.setSelected(false);
        rbCompleta.setSelected(false);
    }
    @FXML
    public void guardarCambiosReserva() throws ExceptionReserva {
        Reserva tempReserva = this.reserva;
            if (isInputValid()) {
                tempReserva.setCodigo(reserva.getCodigo());
                tempReserva.setCliente(reserva.getCliente());
                tempReserva.setFechaLlegada(fechaLlegada.getValue());
                tempReserva.setFechaSalida(fechaSalida.getValue());
                tempReserva.setTipo(Tipo.getTipoByTexto(menuTipo.getValue()));
                tempReserva.setFumador(checkFumador.isSelected());
                if (grupoRegimen.getSelectedToggle() == rbDesayuno) {
                    tempReserva.setAlojamiento(Regimen.DESAYUNO);
                } else if (grupoRegimen.getSelectedToggle() == rbMedia) {
                    tempReserva.setAlojamiento(Regimen.MEDIA);
                } else {
                    tempReserva.setAlojamiento(Regimen.COMPLETA);
                }

                okClicked = true;
                dialogStage.close();
            } else {
                pulsarLimpiarReserva();
            }
    }
    /**
     * Called when the user clicks Cancelar.
     */
    @FXML
    private void cancelarReserva() {
        dialogStage.close();
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
        if(!rbDesayuno.isSelected() && !rbMedia.isSelected() && !rbCompleta.isSelected()) {
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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        if(reserva.getFechaLlegada() != null) {
            fechaLlegada.setValue(reserva.getFechaLlegada());
            fechaSalida.setValue(reserva.getFechaSalida());
            spNumHab.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 1)
            );
            menuTipo.setValue(reserva.getTipo().getTexto());
            checkFumador.setSelected(reserva.isFumador());
            if (checkFumador.isSelected()) {
                txtFumador.setVisible(true);
            } else {
                txtFumador.setVisible(false);
            }
            opcionRegimen(reserva);
        }
    }
    @FXML
    private void clickFumador() {
        if (checkFumador.isSelected()) {
            txtFumador.setVisible(true);
        } else {
            txtFumador.setVisible(false);
        }
    }
    public void setReservaModelo(ReservaModelo modelo) {
        this.modelo = modelo;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
