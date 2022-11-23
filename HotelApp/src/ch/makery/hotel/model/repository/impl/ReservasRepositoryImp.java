package ch.makery.hotel.model.repository.impl;

import ch.makery.hotel.model.ExceptionReserva;
import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.model.ReservaVO;
import ch.makery.hotel.model.repository.ReservasRepository;

import java.sql.Statement;
import java.util.ArrayList;

public class ReservasRepositoryImp implements ReservasRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;
    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExceptionReserva {
        return null;
    }

    @Override
    public void addReserva(Reserva reserva) throws ExceptionReserva {

    }

    @Override
    public void deleteReserva(String dniCliente) throws ExceptionReserva {

    }

    @Override
    public void editReserva(Reserva reserva) throws ExceptionReserva {

    }
}
