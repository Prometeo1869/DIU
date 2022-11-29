package ch.makery.examen.controller;

import ch.makery.examen.Main;
import ch.makery.examen.model.Moneda;
import ch.makery.examen.model.MonedaModelo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class VentanaPrincipalController {

    @FXML
    private TableView<Moneda> tvMoneda;
    @FXML
    private TableColumn<Moneda, String> tColumnMoneda;
    @FXML
    private Label labelTitulo, labelNombre, labelMultiplicador;
    @FXML
    private Separator separador;
    @FXML
    private GridPane detalles;
    private Main main;
    private MonedaModelo monedaModelo;

    public VentanaPrincipalController() {
    }
    @FXML
    private void initialize() {
        //muestra el DNi, nombre y apellido en la tabla
        tColumnMoneda.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        mostrarDetalle(null);

        tvMoneda.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> mostrarDetalle(newValue))
        );
    }

    private void mostrarDetalle(Moneda moneda) {
        if(moneda != null) {
            labelTitulo.setVisible(true);
            separador.setVisible(true);
            detalles.setVisible(true);

            labelNombre.setText(moneda.getNombre());
            labelMultiplicador.setText("" + moneda.getMultiplicador());
        }
    }

    public void setMain(Main main) {
        this.main = main;
        tvMoneda.setItems(main.getMonedaData());
    }

    public void setMonedaModelo(MonedaModelo monedaModelo) {
        this.monedaModelo = monedaModelo;
    }
}
