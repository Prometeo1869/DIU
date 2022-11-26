package ch.makery.hotel.model.repository;

import ch.makery.hotel.model.*;

import java.util.ArrayList;

public interface ReservasRepository {

    ArrayList<ReservaVO> ObtenerListaReservas() throws ExceptionReserva;
    void addReserva(Reserva reserva) throws ExceptionReserva;
    void deleteReserva(String dniCliente) throws ExceptionReserva;
    void editReserva(Reserva reserva) throws ExceptionReserva;
    int totalReservas();
}
