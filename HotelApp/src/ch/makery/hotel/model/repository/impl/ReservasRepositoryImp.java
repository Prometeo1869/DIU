package ch.makery.hotel.model.repository.impl;

import ch.makery.hotel.model.*;
import ch.makery.hotel.model.repository.ReservasRepository;
import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservasRepositoryImp implements ReservasRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;
    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExceptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas ORDER BY fecha_llegada";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                Integer codigo = rs.getInt("codigo");
                String cliente = rs.getString("cliente");
                LocalDate fecha_llegada = rs.getDate("fecha_llegada").toLocalDate();
                LocalDate fecha_salida = rs.getDate("fecha_salida").toLocalDate();
                Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
                boolean fumador = rs.getBoolean("fumador");
                Regimen alojamineto = Regimen.valueOf(rs.getString("regimen"));
                this.reserva = new ReservaVO(codigo, cliente, fecha_llegada, fecha_salida, tipo, fumador, alojamineto);
                reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(conn);

            return this.reservas;
        } catch (SQLException var6) {
            throw new ExceptionReserva("No se ha podido obtener la lista de reservas");
        }
    }

    @Override
    public void addReserva(Reserva reserva) throws ExceptionReserva {
//INSERT INTO `reservas` (`cliente`, `fecha_llegada`, `fecha_salida`, `tipo`, `fumador`, `regimen`, `codigo`) VALUES ('', '', '', '', '', '', NULL)
    }

    @Override
    public void deleteReserva(String dniCliente) throws ExceptionReserva {

    }

    @Override
    public void editReserva(Reserva reserva) throws ExceptionReserva {

    }
}
