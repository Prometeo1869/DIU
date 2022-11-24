package ch.makery.hotel.model;

import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;

import java.sql.Date;
import java.time.LocalDate;

public class ReservaVO {

    Integer codigo;
    private String cliente;
    private LocalDate fechaLlegada;
    private LocalDate fechaSalida;
    private Tipo tipo;
    private boolean fumador;
    private Regimen alojamiento;

    public ReservaVO(Integer codigo, String cliente, LocalDate fechaLlegada, LocalDate fechaSalida, Tipo tipo, boolean fumador, Regimen alojamiento) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.tipo = tipo;
        this.fumador = fumador;
        this.alojamiento = alojamiento;
    }

    public ReservaVO() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public Regimen getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Regimen alojamiento) {
        this.alojamiento = alojamiento;
    }
}
