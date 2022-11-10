package com.tarea;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Properties;
/**
 * @author Juan Cebrian
 * @since 4/10/2022*
 */
public class VistaTabla extends Application {
    private TableView<Personaje> tvPersonajes;
    private ProgressIndicator progress = new ProgressIndicator(0.5);
    private Button btAdd = new Button();
    private Button btDelete = new Button();
    private Label lbNombre = new Label("Nombre:");
    private Label lbApellido = new Label("Apellido:");
    private TextField txNombre = new TextField();
    private TextField txApellido = new TextField();


    final ObservableList<Personaje> personajes = FXCollections.observableArrayList(
            new Personaje("Pepito", "Grillo"),
            new Personaje("Bob", "Esponja"),
            new Personaje("Juan", "Sin Miedo"),
            new Personaje("Perico", "De Los Palotes"),
            new Personaje("Juana", "La Loca"));

    TableColumn<Personaje, String> columnaNombre = new TableColumn<Personaje, String>("Nombre");

    TableColumn<Personaje, String> columnaApellidos = new TableColumn<Personaje, String>("Apellidos");
    public static class Personaje {
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty apellidos;

        private Personaje(String nombre, String apellidos) {
            this.nombre = new SimpleStringProperty(nombre);
            this.apellidos = new SimpleStringProperty(apellidos);
        }

        public String getNombre() {
            return nombre.get();
        }

        public void setNombre(String nombre) {
            this.nombre.set(nombre);
        }

        public String getApellidos() {
            return apellidos.get();
        }

        public void setApellidos(String apellidos) {
            this.apellidos.set(apellidos);
        }
    }
    private void anadirPersonaje() {
        if (txNombre.getText().equals("") || txApellido.getText().equals("")) {
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("Error");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Existe algun campo vacio");
            Optional<ButtonType> respuesta = dialogo.showAndWait();
        } else if (personajes.size() < 10){
            Personaje personaje = new Personaje(txNombre.getText(), txApellido.getText());
            personajes.add(personaje);
            Number tamano = personajes.size();
            progress.setProgress(tamano.doubleValue() / 10);
        } else if (personajes.size() == 10) {
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("Error");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Número máximo de personajes añadidos");
            Optional<ButtonType> respuesta = dialogo.showAndWait();
        }
    }
    public void eliminarPersona(){
        if(tvPersonajes.getSelectionModel().getSelectedItem() != null){
            //Dialogo confirmacion
            Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
            dialogo.setTitle("Confirmar");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Estás seguro de que quieres eliminar el prsonaje seleccionado?");

            Optional<ButtonType> respuesta = dialogo.showAndWait();
            if (respuesta.get() == ButtonType.OK){
                personajes.remove(tvPersonajes.getSelectionModel().getSelectedItem());
                Number tamano = personajes.size();
                progress.setProgress(tamano.doubleValue()/10);
            } else {
                System.out.println("No se ha borrado el personaje");
            }
        }else{
            Alert dialogo = new Alert(Alert.AlertType.ERROR);
            dialogo.setTitle("Error");
            dialogo.setHeaderText(null);
            dialogo.setContentText("No se ha seleccionado un personaje.");

            dialogo.showAndWait();
        }
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            HBox raiz = new HBox();
            raiz.setPadding(new Insets(40));
            raiz.setSpacing(10);
            VBox izquierda= new VBox();
            izquierda.setPadding(new Insets(40));
            izquierda.setSpacing(10);
            VBox derecha = new VBox();
            derecha.setPadding(new Insets(40));
            derecha.setSpacing(10);
            HBox botones = new HBox();
            botones.setPadding(new Insets(10));
            botones.setSpacing(40);
            HBox btProgreso = new HBox();
            btProgreso.setPadding(new Insets(10));
            btProgreso.setSpacing(10);

            Label lbPersonajes = new Label("Personajes:");
            tvPersonajes = new TableView<Personaje>();
            tvPersonajes.getColumns().add(columnaNombre);
            tvPersonajes.getColumns().add(columnaApellidos);
            tvPersonajes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tvPersonajes.setMaxWidth(303);
            tvPersonajes.setMaxHeight(290);
            columnaNombre.setMinWidth(100);
            columnaNombre.setMaxWidth(200);
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Personaje, String>("Nombre"));
            columnaApellidos.setMinWidth(200);
            columnaApellidos.setMaxWidth(500);
            columnaApellidos.setCellValueFactory(new PropertyValueFactory<Personaje, String>("Apellidos"));

            tvPersonajes.setItems(personajes);

            btAdd.setText("Añadir");
            btDelete.setText("Eliminar");

            btProgreso.getChildren().add(progress);
            botones.getChildren().addAll(btAdd, btDelete);
            izquierda.getChildren().addAll(lbPersonajes, tvPersonajes, btProgreso, botones);
            derecha.getChildren().addAll(lbNombre, txNombre, lbApellido, txApellido);
            raiz.getChildren().addAll(izquierda, derecha);
            btProgreso.setAlignment(Pos.CENTER);
            botones.setAlignment(Pos.CENTER);

            btDelete.setOnAction(e -> eliminarPersona());
            btAdd.setOnAction(e -> anadirPersonaje());
            Number tamano = personajes.size();
            progress.setProgress(tamano.doubleValue()/10);

            Scene escena = new Scene(raiz, 700, 650);
            escena.getStylesheets().add(getClass().getResource("/css/aplicacion.css").toExternalForm());
            btAdd.setId("add");
            btDelete.setId("eliminar");
            progress.setId("progreso");
            escena.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent keyevent){
                    if(keyevent.getCode() == KeyCode.ENTER){
                        anadirPersonaje();
                    }
                }
            });
            tvPersonajes.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent keyevent){
                    if(keyevent.getCode() == KeyCode.ENTER){
                        anadirPersonaje();
                    }
                }
            });
            escenarioPrincipal.setTitle("Vista de tabla");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}