package ch.makery.examen.controller;
import Modelo.ExcepcionMoneda;
import ch.makery.examen.Main;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VentanaPrincipalController {

    @FXML
    private Label moneda2;
    @FXML
    private TextField valor1, valor2;
    @FXML
    private ChoiceBox<String> chMonedas;
    private Main main;
    float multiplicador = 1;
    private MonedaModelo monedaModelo;

    public VentanaPrincipalController() {
    }
    @FXML
    private void initialize() throws ExcepcionMoneda {
        //Rescatar los cambios de elección de moneda
        chMonedas.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> cambiarMoneda(newValue))
        );
    }
    //Cambia la moneda con la se va a hacer la conversion cuando la eliges en el Menu(ChoiceBox)
    private void cambiarMoneda(String nombreMoneda) {
        if(nombreMoneda != null) {
            moneda2.setText(nombreMoneda);
            for(Moneda m: this.main.getMonedaData()) {
                if(nombreMoneda.equals(m.getNombre())) {
                    this.multiplicador = m.getMultiplicador();
                }
            }
        }
        valor2.setText("");
    }
    //Referencia al Main
    public void setMain(Main main) {
        this.main = main;
    }

    //Métodos de validación y conversión que ocurren cuando se pulsa el boton "Convertir"
    public void pulsarConvertir() throws NumberFormatException {
        boolean esValido = true;
        if(chMonedas.getValue() == null) {
            esValido = false;
        }
        if(esValido) {
            for (int i = 0; i < valor1.getText().length() && esValido; i++) {
                if (Character.isLetter(valor1.getText().charAt(i))) {
                    esValido = false;
                }
            }
        }
        if(esValido) {
            for(int i = 0; i < valor2.getText().length() && esValido; i++) {
                if(Character.isLetter(valor2.getText().charAt(i))) {
                    esValido = false;
                }
            }
        }
        if(esValido) {
            if (valor2.getText().equals("")) {
                if(Float.parseFloat(valor1.getText()) >= 0) {
                    float resultado = multiplicador * Float.parseFloat(valor1.getText());
                    valor2.setText("" + resultado);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Valor negativo");
                    alert.setContentText("Por favor escriba una cifra positiva");
                    alert.showAndWait();
                    valor1.setText("");
                    valor2.setText("");
                }
            }
            if (valor1.getText().equals("")) {
                if(Float.parseFloat(valor2.getText()) >= 0) {
                    float resultado = Float.parseFloat(valor2.getText()) * (2 - multiplicador);
                    valor1.setText("" + resultado);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Valor negativo");
                    alert.setContentText("Por favor escriba una cifra positiva");
                    alert.showAndWait();
                    valor1.setText("");
                    valor2.setText("");
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato incorrecto");
            alert.setContentText("Por favor rellene los campos correctamente");
            alert.showAndWait();
            valor1.setText("");
            valor2.setText("");
        }

    }
    //Método que al pulsar el botón "Eliminar" elimine tanto del modelo como de la base de datos
    //la moneda que esté en ese momento elegida en el menu(ChoiceBox)
    public void pulsarEliminar(ActionEvent actionEvent) throws ExcepcionMoneda {
        valor1.setText("");
        valor2.setText("");
        moneda2.setText("");

        String nombreMoneda = chMonedas.getValue();
        chMonedas.getItems().removeAll(nombreMoneda);
        for(Moneda m: main.getMonedaData()) {
            if(m.getNombre().equals(nombreMoneda)) {
                int codigo = m.getCodigo();
                monedaModelo.getRepos().deleteMoneda(codigo);
            }
        }
        monedaModelo.restarMoneda();
    }
    // Método para que la pulsar ENTER se realice el método pulsarConvertir
    public void pulsarEnter(KeyEvent keyEvent) {
        try {
            if (!valor1.getText().equals("")) {
                valor1.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        pulsarConvertir();
                    }
                });
            }
            if (!valor2.getText().equals("")) {
                valor2.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        pulsarConvertir();
                    }
                });
            }
        } catch (NullPointerException e) {

        }
    }
    //Reerencia al modelo
    public void setMonedaModelo(MonedaModelo monedaModelo) {
        this.monedaModelo = monedaModelo;
    }
    //Referencia al repositorio
    public void setMonedaData(ObservableList<Moneda> monedaData) {
        for(Moneda m: monedaData) {
            chMonedas.getItems().add(m.getNombre());
        }
    }
}
