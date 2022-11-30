package ch.makery.examen.controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import ch.makery.examen.Main;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;


public class VentanaSecundariaController {

    @FXML
    private Label numMonedas;

    @FXML
    private void initialize() throws ExcepcionMoneda {

    }

    public void setMonedaData(ArrayList<MonedaVO> obtenerListaMonedas) {
        int num = 0;
        for(MonedaVO m: obtenerListaMonedas) {
            num++;
        }
        numMonedas.setText("" + num);
    }
}
