package ch.makery.hotel.model;

public class ExceptionReserva extends Exception {
    private String mensaje;

    public ExceptionReserva() {
    }

    /**
     *
     * @param ms
     */
    public ExceptionReserva(String ms) { this.mensaje = ms;}

    /**
     *
     * @return mensaje
     */
    public String imprimirMensaje() { return this.mensaje; }
}
