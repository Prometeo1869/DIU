package controlador;

import com.almasb.fxgl.ui.ProgressBar;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class VentanaBarraProgresoControlador {

    @FXML
    Button btAdd;
    @FXML
    Button btDel;
    @FXML
    ProgressBar progreso;
    @FXML
    TableView<Personaje> tabla;

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
    
}
