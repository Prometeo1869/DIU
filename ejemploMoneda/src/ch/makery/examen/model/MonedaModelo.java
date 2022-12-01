package ch.makery.examen.model;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class MonedaModelo {
    private MonedaRepository repos;
    IntegerProperty numero = new SimpleIntegerProperty(0);
    //private Moneda
    public MonedaModelo() {

    }

    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public MonedaRepository getRepos() {
        return repos;
    }

    public void setRepos(MonedaRepository repos) {
        this.repos = repos;
    }

    public ArrayList<MonedaVO> obtenerListaMonedas() throws ExcepcionMoneda {
        numero = new SimpleIntegerProperty(this.repos.ObtenerListaMonedas().size());
        return this.repos.ObtenerListaMonedas();
    }

    public IntegerProperty getNumeroMonedas() {
        return numero;
    }

    public void restarMoneda() {
        this.setNumero(this.getNumero() - 1);
    }
}