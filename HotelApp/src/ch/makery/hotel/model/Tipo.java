package ch.makery.hotel.model;

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
}
