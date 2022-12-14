package ch.makery.hotel.model.repository.impl;

import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.ClienteVO;
import ch.makery.hotel.model.ExceptionCliente;
import ch.makery.hotel.model.repository.ClienteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Juan Cebrián
 */
public class ClienteRepositoryImpl implements ClienteRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    /**
     *
     * @return
     * @throws ExceptionCliente
     */
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExceptionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM cliente ORDER BY apellidos, nombre";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String dieccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, nombre, apellidos, dieccion, localidad, provincia);
                clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(conn);

            return this.clientes;
        } catch (SQLException var6) {
            throw new ExceptionCliente("No se ha podido obtener la lista de clientes");
        }
    }

    /**
     *
     * @param cliente
     * @throws ExceptionCliente
     */
    public void addCliente(Cliente cliente) throws ExceptionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO cliente (dni, nombre, apellidos, direccion, localidad, provincia) VALUES('" +
                    cliente.getDni() + "','" + cliente.getNombre() + "','" + cliente.getApellidos() + "','" + cliente.getDireccion() + "','" +
                    cliente.getLocalidad() + "','" + cliente.getProvincia() + "')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExceptionCliente("No se ha podido añadir el cliente");
        }
    }

    /**
     *
     * @param dniCliente
     * @throws ExceptionCliente
     */
    public void deleteCliente(String dniCliente) throws ExceptionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM cliente WHERE dni = '%s'", dniCliente);
            stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var) {
            throw new ExceptionCliente("No se ha podido relaizar la eliminación");
        }
    }

    /**
     *
     * @param cliente
     * @throws ExceptionCliente
     */
    public void editCliente(Cliente cliente) throws ExceptionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE cliente SET " +
                            "dni='" + cliente.getDni() +
                    "', nombre='" + cliente.getNombre() +
                    "', apellidos='" + cliente.getApellidos() +
                    "', direccion='" + cliente.getDireccion() +
                    "', localidad='" + cliente.getLocalidad() +
                    "', provincia='" + cliente.getProvincia() +
                    "' WHERE dni='" +cliente.getDni() + "'");
            this.stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (Exception var4) {
            throw new ExceptionCliente("No se ha podido editar");
        }
    }
}
