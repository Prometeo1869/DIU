package diu.iesvalleinclan.agenda.service;

import diu.iesvalleinclan.agenda.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO createdPerson(PersonDTO dto);

    List<PersonDTO> getByLastName(String lastName);

    List<PersonDTO> getPersons();

    PersonDTO getById(Integer id);

    PersonDTO updatePerson(Integer id, PersonDTO dto);

    boolean exist(Integer id);

    void deletePerson(Integer id);

    long count();

    void deleteAllPersons();

}
