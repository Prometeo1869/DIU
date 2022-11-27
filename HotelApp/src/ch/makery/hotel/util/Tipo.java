package ch.makery.hotel.util;

/**
 * @author Juan Cebrian
 */
public enum Tipo {
    INDIVIDUAL("Doble de uso individual", 20),
    DOBLE("Doble", 80),
    JUNIOR("Junior suite", 15),
    SUITE("Suite", 5);

    private final String texto;
    private int cantidad;

    Tipo(String texto, int cantidad) {
        this.texto = texto;
        this.cantidad = cantidad;
    }

    public String getTexto() {
        return texto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public static Tipo getTipoByTexto(String texto) {
        //Doble de uso individual -> INDIVIDUAL
        if (texto.equals("Doble de uso individual")) {
            return Tipo.INDIVIDUAL;
        }
        //Doble -> DOBLE
        if (texto.equals("Doble")) {
            return Tipo.DOBLE;
        }
        //Junior suite -> JUNIOR
        if (texto.equals("Junior suite")) {
            return Tipo.JUNIOR;
        }
        //Suite -> SUITE
        if (texto.equals("Suite")) {
            return Tipo.SUITE;
        }
        //RESTO
        else {
            return null;
        }
    }
}
