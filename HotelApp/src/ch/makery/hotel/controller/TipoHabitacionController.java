package ch.makery.hotel.controller;

import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.util.Tipo;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

import java.time.LocalDate;
import java.util.ArrayList;

public class TipoHabitacionController {

    @FXML
    private ProgressBar pbIndividual, pbDoble, pbJunior, pbSuite;

    @FXML
    private void initialize() {

    }

    public void calculoProgressBar(ArrayList<Reserva> listaReservasTotales) {
        double contadorINDIVIDUAL = 0;
        double contadorDOBLE = 0;
        double contadorJUNIOR = 0;
        double contadorSUITE = 0;
        //Cuenta número de reservas por habitación actuales
        for(Reserva r: listaReservasTotales) {
            if (r.getFechaLlegada().compareTo(LocalDate.now()) < 0 && r.getFechaSalida().compareTo(LocalDate.now()) > 0) {
                if(r.getTipo().equals(Tipo.INDIVIDUAL)) {
                    contadorINDIVIDUAL++;
                }
                if(r.getTipo().equals(Tipo.DOBLE)) {
                    contadorDOBLE++;
                }
                if(r.getTipo().equals(Tipo.JUNIOR)) {
                    contadorJUNIOR++;
                }
                if(r.getTipo().equals(Tipo.SUITE)) {
                    contadorSUITE++;
                }
            }
        }
        pbIndividual.setProgress(contadorINDIVIDUAL * 20 / 100);
        pbDoble.setProgress(contadorDOBLE * 80 / 100);
        pbJunior.setProgress(contadorJUNIOR * 15 / 100);
        pbSuite.setProgress(contadorSUITE * 5 / 100);
    }
}
