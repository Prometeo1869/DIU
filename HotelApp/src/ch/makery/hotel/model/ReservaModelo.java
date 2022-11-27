package ch.makery.hotel.model;

import ch.makery.hotel.model.repository.ReservasRepository;
import java.util.ArrayList;

/**
 * @author Juan Cebrian
 */
public class ReservaModelo {

    ReservasRepository rep;

    public ReservasRepository getRep() {
        return rep;
    }
    public void setRep(ReservasRepository rep) {
        this.rep = rep;
    }
    public ArrayList<ReservaVO> obtenerReservas() throws ExceptionReserva {
        return this.rep.ObtenerListaReservas();
    }

}
