package ch.makery.hotel.util;

public enum Regimen {
    DESAYUNO("Alojamiento y desayuno"),
    MEDIA("Media pensión"),
    COMPLETA("Pensión completa");

    private String texto;

    Regimen(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
