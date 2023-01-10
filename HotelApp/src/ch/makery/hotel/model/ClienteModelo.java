package ch.makery.hotel.model;

import ch.makery.hotel.model.repository.ClienteRepository;

import java.util.ArrayList;

/**
 * @author Juan Cebrian
 */
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

    public void borrarCliente(String dni) throws ExceptionCliente {
            this.rep.deleteCliente(dni);
    }

    public void addCliente(Cliente tempCliente) throws ExceptionCliente {
        this.rep.addCliente(tempCliente);
    }

    public void editarCliente(Cliente selectedCliente) throws ExceptionCliente {
        this.rep.editCliente(selectedCliente);
    }
}
