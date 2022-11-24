package ch.makery.hotel.model;

import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;
import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {

    private IntegerProperty codigo;
    private StringProperty cliente;
    private ObjectProperty<LocalDate> fechaLlegada;
    private ObjectProperty<LocalDate> fechaSalida;
    private ObjectProperty<Tipo> tipo;
    private BooleanProperty fumador;
    private ObjectProperty<Regimen> alojamiento;

    public Reserva(Integer codigo, String cliente, LocalDate fechaLlegada, LocalDate fechaSalida, Tipo tipo, boolean fumador, Regimen alojamiento) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.cliente = new SimpleStringProperty(cliente);
        this.fechaLlegada = new SimpleObjectProperty<LocalDate>(LocalDate.parse(fechaLlegada.toString()));
        this.fechaSalida = new SimpleObjectProperty<LocalDate>(LocalDate.parse(fechaSalida.toString()));
        this.tipo = new SimpleObjectProperty<Tipo>(tipo);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.alojamiento = new SimpleObjectProperty<Regimen>(alojamiento);
    }

    public Reserva() {
        this.codigo = new SimpleIntegerProperty(0);
        this.cliente = new SimpleStringProperty("");
        this.fechaLlegada = new SimpleObjectProperty<LocalDate>(null);
        this.fechaSalida = new SimpleObjectProperty<LocalDate>(null);
        this.tipo = new SimpleObjectProperty<Tipo>(null);
        this.fumador = new SimpleBooleanProperty(false);
        this.alojamiento = new SimpleObjectProperty<Regimen>(null);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public String getCliente() {
        return cliente.get();
    }

    public StringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada.get();
    }

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada.set(fechaLlegada);
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public Tipo getTipo() {
        return tipo.get();
    }

    public ObjectProperty<Tipo> tipoProperty() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo.set(tipo);
    }

    public boolean isFumador() {
        return fumador.get();
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public Regimen getAlojamiento() {
        return alojamiento.get();
    }

    public ObjectProperty<Regimen> alojamientoProperty() {
        return alojamiento;
    }

    public void setAlojamiento(Regimen alojamiento) {
        this.alojamiento.set(alojamiento);
    }
}
