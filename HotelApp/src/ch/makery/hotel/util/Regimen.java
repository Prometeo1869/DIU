package ch.makery.hotel.util;

/**
 * @author Juan Cebrian
 */
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

    public static Regimen getRegimenByTexto(String texto) {
        //Alojamiento y desayuno -> DESAYUNO
        if (texto.equals("Alojamiento y desayuno")) {
            return Regimen.DESAYUNO;
        }
        //Media pensión -> MEDIA
        if (texto.equals("Media pensión")) {
            return Regimen.MEDIA;
        }
        //Pensión completa -> COMPLETA
        if (texto.equals("Pensión completa")) {
            return Regimen.COMPLETA;
        }
        //RESTO
        else {
            return null;
        }
    }
}
