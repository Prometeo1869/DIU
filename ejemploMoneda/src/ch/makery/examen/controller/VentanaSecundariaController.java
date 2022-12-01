package ch.makery.examen.controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import ch.makery.examen.Main;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;


public class VentanaSecundariaController {

    @FXML
    private Label numMonedas;
    private IntegerProperty numero = new SimpleIntegerProperty(0);
    MonedaModelo modelo;

    @FXML
    private void initialize() throws ExcepcionMoneda {

    }

    //Hace que cuando el número de monedas cambie en el modelo también cambie en el Label de esta ventana
    public void numeroMonedas() {

        numero.addListener((observable, oldValue, newValue) -> numMonedas.setText("" + newValue));
        numero.bindBidirectional(modelo.getNumeroMonedas());
    }
    //Referencia al modelo
    public void setModelo(MonedaModelo modelo) {
        this.modelo = modelo;
    }
}