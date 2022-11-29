package ch.makery.hotel.model.repository.impl;

import ch.makery.hotel.model.*;
import ch.makery.hotel.model.repository.ReservasRepository;
import ch.makery.hotel.util.Regimen;
import ch.makery.hotel.util.Tipo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Juan Cebrian
 */
public class ReservasRepositoryImp implements ReservasRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;
    private Cliente cliente;

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExceptionReserva {

        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas WHERE cliente='" + cliente.getDni() + "' ORDER BY fecha_llegada";
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

    public void setCliente(Cliente c) {
        this.cliente = c;
    }
    @Override
    public void addReserva(Reserva reserva) throws ExceptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO reservas (cliente, fecha_llegada, fecha_salida, tipo, fumador, regimen, codigo) VALUES('" +
                reserva.getCliente() + "', '" + reserva.getFechaLlegada() + "', '" + reserva.getFechaSalida() + "', '" +
                reserva.getTipo() + "', " + reserva.isFumador() + ", '" + reserva.getAlojamiento() + "', " + reserva.getCodigo() + ")";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExceptionReserva("No se ha podido añadir la reserva");
        }
    }

    @Override
    public void deleteReserva(int codigo) throws ExceptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM reservas WHERE codigo=" + codigo + "");
            stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var) {
            throw new ExceptionReserva("No se ha podido relaizar la eliminación");
        }
    }

    @Override
    public void editReserva(Reserva reserva) throws ExceptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE reservas SET " +
                    "cliente='" + reserva.getCliente() +
                    "', fecha_llegada='" + reserva.getFechaLlegada() +
                    "', fecha_salida='" + reserva.getFechaSalida() +
                    "', tipo='" + reserva.getTipo() +
                    "', fumador=" + reserva.isFumador() +
                    ", regimen='" + reserva.getAlojamiento() +
                    "',codigo=" + reserva.getCodigo() +
                    " WHERE codigo=" +reserva.getCodigo() + "");
            this.stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (Exception var4) {
            throw new ExceptionReserva("No se ha podido editar");
        }
    }
    public int elegirCodigoLibre() {
        int codigo = 1;
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas ORDER BY codigo";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);
            while (rs.next()) {
                if (codigo == rs.getInt("codigo")) {
                    codigo++;
                } else {
            this.stmt.close();
            this.conexion.desconectarBD(conn);
            return codigo;
                }
            }
            return codigo;
        } catch (SQLException e) {
            return codigo;
        }
    }

    public ArrayList<Reserva> listaReservasTotales() {
        ArrayList<Reserva> lista = new ArrayList<>();
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas ORDER BY codigo";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);
            while (rs.next()) {
                Reserva r = new Reserva(//Integer codigo, String cliente, LocalDate fechaLlegada, LocalDate fechaSalida, Tipo tipo, boolean fumador, Regimen alojamiento) {
                        rs.getInt("codigo"),
                        rs.getString("cliente"),
                        rs.getDate("fecha_llegada").toLocalDate(),
                        rs.getDate("fecha_salida").toLocalDate(),
                        Tipo.valueOf(rs.getObject("tipo").toString()),
                        rs.getBoolean("fumador"),
                        Regimen.valueOf(rs.getObject("regimen").toString())
                );
                lista.add(r);
            }
            this.stmt.close();
            this.conexion.desconectarBD(conn);
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

}
