package diu.iesvalleinclan.agenda.service;

import diu.iesvalleinclan.agenda.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO createdPerson(PersonDTO dto);

    List<PersonDTO> getByLastName(String lastName);

    List<PersonDTO> getPersons();

    PersonDTO getById(String id);

    PersonDTO updatePerson(String id, PersonDTO dto);

    boolean exist(String id);

    void deletePerson(String id);

    long count();

    void deleteAllPersons();

}
