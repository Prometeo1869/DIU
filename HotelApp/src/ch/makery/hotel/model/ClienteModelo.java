package ch.makery.hotel.model;

import ch.makery.hotel.model.repository.ClienteRepository;

import java.util.ArrayList;

public class ClienteModelo {

    ClienteRepository rep;

    public ClienteRepository getRep() {
        return rep;
    }
    public void setRep(ClienteRepository rep) {
        this.rep = rep;
    }
    public ArrayList<ClienteVO> obtenerClientes() throws ExceptionCliente {
        return this.rep.ObtenerListaClientes();
    }
}
