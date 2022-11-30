package ch.makery.examen.controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import ch.makery.examen.Main;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import ch.makery.examen.util.Convert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class VentanaPrincipalController {

    @FXML
    private Label moneda1, moneda2;
    @FXML
    private TextField valor1, valor2;
    @FXML
    private ChoiceBox<String> chMonedas;
    private Main main;
    float multiplicador = 1;
    ObservableList<Moneda> listaMonedas = FXCollections.observableArrayList();

    public VentanaPrincipalController() {
        try {
            if (!valor1.getText().equals("")) {
                valor1.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        pulsarConvertir();
                    }
                });
            }
        } catch (NullPointerException e) {

        }
    }
    @FXML
    private void initialize() throws ExcepcionMoneda {

        chMonedas.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> cambiarMoneda(newValue))
        );
    }

    private void cambiarMoneda(String nombreMoneda) {
        if(nombreMoneda != null) {
            moneda2.setText(nombreMoneda);
            for(Moneda m: listaMonedas) {
                if(nombreMoneda.equals(m.getNombre())) {
                    this.multiplicador = m.getMultiplicador();
                }
            }
        }
        valor2.setText("");
    }

    public void setMain(Main main) {
        this.main = main;
        //tvMoneda.setItems(main.getMonedaData());
    }

    public void setMonedaData(ArrayList<MonedaVO> obtenerListaMonedas) {
        for(MonedaVO m: obtenerListaMonedas) {
            chMonedas.getItems().add(m.getNombre());
            listaMonedas.add(Convert.convertTo(m));
        }
    }

    public void pulsarConvertir() {
        if(valor2.getText().equals("")) {
            float resultado = multiplicador * Float.valueOf(valor1.getText());
            valor2.setText("" + resultado);
        }
        if(valor1.getText().equals("")) {
            float resultado = Float.valueOf(valor2.getText()) * (2 - multiplicador);
            valor1.setText("" + resultado);
        }
    }

    public void pulsarEliminar(ActionEvent actionEvent) {
        valor1.setText("");
        valor2.setText("");
        moneda2.setText("");
        //chMonedas.setItems(null);
    }
}
