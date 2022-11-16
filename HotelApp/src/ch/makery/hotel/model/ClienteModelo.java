package ch.makery.hotel.model;

import ch.makery.hotel.model.repository.impl.ClienteRepositoryImpl;

import java.util.ArrayList;

public class ClienteModelo {

    ClienteRepositoryImpl rep;

    public ClienteRepositoryImpl getRep() {
        return rep;
    }
    public void setRep(ClienteRepositoryImpl rep) {
        this.rep = rep;
    }
    public ArrayList<Cliente> obtenerClientes() throws ExceptionCliente {
        return this.rep.ObtenerListaClientes();
    }
}
