package ch.makery.hotel.model.repository;

import ch.makery.hotel.model.Cliente;
import ch.makery.hotel.model.ClienteVO;
import ch.makery.hotel.model.ExceptionCliente;

import java.util.ArrayList;

public interface ClienteRepository {

    ArrayList<ClienteVO> ObtenerListaClientes() throws ExceptionCliente;
    void addCliente(Cliente cliente) throws ExceptionCliente;
    void deleteCliente(String dniCliente) throws ExceptionCliente;
    void editCliente(Cliente cliente) throws ExceptionCliente;
}
