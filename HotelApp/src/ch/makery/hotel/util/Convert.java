package ch.makery.hotel.util;

import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.ClienteVO;
import ch.makery.hotel.model.Reserva;
import ch.makery.hotel.model.ReservaVO;

import java.time.LocalDate;

/**
 * Clase convertidor de Cliente a ClienteVO y viceversa
 *
 * @author Juan Cebrián
 */
public class Convert {

    /**
     * Convierte un objeto de la clase ClienteVO en otro de la clase Cliente
     *
     * @param cvo
     * @return c
     */
    public static Cliente convertTo(ClienteVO cvo) {
        Cliente c = new Cliente(
                cvo.getDni(),
                cvo.getNombre(),
                cvo.getApellidos(),
                cvo.getDireccion(),
                cvo.getLocalidad(),
                cvo.getProvincia()
        );
        return c;
    }

    /**
     * Convierte un objeto de la clase Cliente en otro de la clase ClienteVO
     *
     * @param c
     * @return cvo
     */
    public static ClienteVO convertTo(Cliente c) {
        ClienteVO cvo = new ClienteVO(
                c.getDni(),
                c.getNombre(),
                c.getApellidos(),
                c.getDireccion(),
                c.getLocalidad(),
                c.getProvincia()
        );
        return cvo;
    }

    public static Reserva convertTo(ReservaVO rvo) {
        Reserva r = new Reserva(
                rvo.getCodigo(),
                rvo.getCliente(),
                rvo.getFechaLlegada(),
                rvo.getFechaSalida(),
                rvo.getTipo(),
                rvo.isFumador(),
                rvo.getAlojamiento()
        );
        return r;
    }

    public static ReservaVO convertTo(Reserva r) {
        ReservaVO rvo = new ReservaVO(
                r.getCodigo(),
                r.getCliente(),
                r.getFechaLlegada(),
                r.getFechaSalida(),
                r.getTipo(),
                r.isFumador(),
                r.getAlojamiento()
        );
        return rvo;
    }
}
