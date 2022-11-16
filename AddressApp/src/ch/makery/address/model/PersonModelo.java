package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;

import java.util.ArrayList;

public class PersonModelo {

    private PersonRepository repos;
    public PersonModelo() {

    }
    public PersonRepository getRepos() {
        return repos;
    }
    public void setRepos(PersonRepository repos) {
        this.repos = repos;
    }
    public ArrayList<PersonVO> obtenerListaPerson() throws ExcepcionPerson {
        return this.repos.ObtenerListaPerson();
    }
}
