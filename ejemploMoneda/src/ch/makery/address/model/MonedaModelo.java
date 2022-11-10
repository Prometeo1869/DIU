package ch.makery.address.model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class MonedaModelo {
    private MonedaRepository repos;
    //private Moneda
    public MonedaModelo() {

    }


    public MonedaRepository getRepos() {
        return repos;
    }

    public void setRepos(MonedaRepository repos) {
        this.repos = repos;
    }

    public ArrayList<MonedaVO> obtenerListaMonedas() throws ExcepcionMoneda {
        return this.repos.ObtenerListaMonedas();
    }
}