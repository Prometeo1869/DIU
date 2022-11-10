package ch.makery.address.model.repository.impl;

import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Juan Cebrián
 */
public class PersonRepositoryImpl implements PersonRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    /**
     *
     * @return
     * @throws ExcepcionPerson
     */
    @Override
    public ArrayList<PersonVO> ObtenerListaPerson() throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Person";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                int code = rs.getInt("code");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String street = rs.getString("street");
                int postalCode = rs.getInt("postalCode");
                String city = rs.getString("city");
                Date birthday = rs.getDate("birthday");
                this.persona = new PersonVO(code, firstName, lastName, street, postalCode, city, birthday);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    /**
     *
     * @param p
     * @throws ExcepcionPerson
     */
    @Override
    public void addPerson(PersonVO p) throws ExcepcionPerson {
        try {
            p.setCode(this.lastId() + 1);
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Person (code, firstName, lastName, street, postalCode, city, birthday) VALUES(" +
                    p.getCode() + ",'" + p.getFirstName() + "','" + p.getLastName() + "'," + "'" + p.getStreet() + "'," +
                    p.getPostalCode() + ",'" + p.getCity() + "','" + p.getBirthday() + "')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    /**
     *
     * @param codePerson
     * @throws ExcepcionPerson
     */
    @Override
    public void deletePerson(Integer codePerson) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Person WHERE code = %d", codePerson);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido relaizr la eliminación");
        }
    }

    /**
     *
     * @param p
     * @throws ExcepcionPerson
     */
    @Override
    public void editPerson(PersonVO p) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Person SET code = %d, firstName = '%s', lastName '%s', street '%s', " +
                    "postalCode %d, city '%s', birthday '%t' WHERE code = %d", p.getCode(), p.getFirstName(), p.getLastName(),
                    p.getStreet(), p.getPostalCode(), p.getCity(), p.getBirthday());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPerson("No se ha podido relaizr la edición");
        }
    }

    /**
     *
     * @return
     * @throws ExcepcionPerson
     */
    @Override
    public int lastId() throws ExcepcionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT code FROM Person ORDER BY code DESC LIMIT 1");
                registro.next(); lastPersonId = registro.getInt("code")) {
            }

            return lastPersonId;
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}
