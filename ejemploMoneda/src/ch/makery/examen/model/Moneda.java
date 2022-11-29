package ch.makery.examen.model;

import javafx.beans.property.*;

public class Moneda {

    private StringProperty nombre;
    private IntegerProperty codigo;
    private FloatProperty multiplicador;

    public Moneda(String nombre, float multiplicador) {
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
    }
    public Moneda(String nombre, float multiplicador, Integer codigo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
        this.codigo = new SimpleIntegerProperty(codigo);
    }
    public String getNombre() {
        return nombre.get();
    }
    public StringProperty nombreProperty() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
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
    public float getMultiplicador() {
        return multiplicador.get();
    }
    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }
    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }
    @Override
    public String toString() {
        return "Moneda{" +
                "nombre=" + nombre +
                ", multiplicador=" + multiplicador +
                '}';
    }
}
