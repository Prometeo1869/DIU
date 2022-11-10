package ch.makery.address.model;

/**
 * @author Juan Cebrián
 */
public class ExcepcionPerson extends Exception {
    private String mensaje;

    public ExcepcionPerson() {
    }

    /**
     *
     * @param ms
     */
    public ExcepcionPerson(String ms) { this.mensaje = ms;}

    /**
     *
     * @return
     */
    public String imprimirMensaje() { return this.mensaje; }
}
