package ch.makery.hotel.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Reservas {

    private StringProperty cliente;
    private ObjectProperty<Date> fechaLlegada;
    private ObjectProperty<Date> fechaSalida;
    private ObjectProperty<Tipo> tipo;
    private BooleanProperty fumador;
    private ObjectProperty<Regimen> alojamiento;

    public Reservas(StringProperty cliente, ObjectProperty<Date> fechaLlegada, ObjectProperty<Date> fechaSalida, ObjectProperty<Tipo> tipo, BooleanProperty fumador, ObjectProperty<Regimen> alojamiento) {
        this.cliente = cliente;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.tipo = tipo;
        this.fumador = fumador;
        this.alojamiento = alojamiento;
    }
}
