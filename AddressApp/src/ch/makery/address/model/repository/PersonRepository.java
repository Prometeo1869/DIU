package ch.makery.address.model.repository;

import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import java.util.ArrayList;

/**
 * @author Juan Cebrián
 */
public interface PersonRepository {
    /**
     * @return
     * @throws ExcepcionPerson
     */
    ArrayList<PersonVO> ObtenerListaPerson() throws ExcepcionPerson;

    /**
     * @param var1
     * @throws ExcepcionPerson
     */
    void addPerson(PersonVO var1) throws ExcepcionPerson;

    /**
     * @param var1
     * @throws ExcepcionPerson
     */
    void deletePerson(Integer var1) throws ExcepcionPerson;

    /**
     * @param var1
     * @throws ExcepcionPerson
     */
    void editPerson(PersonVO var1) throws ExcepcionPerson;

    /**
     * @return
     * @throws ExcepcionPerson
     */
    int lastId() throws ExcepcionPerson;
}
