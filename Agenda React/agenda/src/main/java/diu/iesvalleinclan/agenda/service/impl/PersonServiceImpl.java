package diu.iesvalleinclan.agenda.service.impl;

import diu.iesvalleinclan.agenda.model.Person;
import diu.iesvalleinclan.agenda.model.dto.PersonDTO;
import diu.iesvalleinclan.agenda.repository.Connection;
import diu.iesvalleinclan.agenda.service.PersonService;
import diu.iesvalleinclan.agenda.util.PersonConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final Connection conn;
    private final PersonConvert mapper;
    @Override
    public PersonDTO createdPerson(PersonDTO dto) {
        return mapper.convertToDTO(conn.save(mapper.convertTo(dto)));
    }

    @Override
    public List<PersonDTO> getByLastName(String lastName) {
        return conn.findByLastName(lastName).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PersonDTO> getPersons() {
        return conn.findAll().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PersonDTO getById(String id) {
        Person person = conn.findById(id).orElse(null);
        return person != null ? mapper.convertToDTO(person) : null;
    }

    @Override
    public PersonDTO updatePerson(String id, PersonDTO dto) {
        Person updating = conn.findById(id).orElse(null);
        if (updating == null) {
            return null;
        } else {
            updating.setFirstName(dto.getFirstName());
            updating.setLastName(dto.getLastName());
            updating.setStreet(dto.getStreet());
            updating.setPostalCode(dto.getPostalCode());
            updating.setCity(dto.getCity());
            updating.setBirthday(dto.getBirthday());
            conn.save(updating);
            return mapper.convertToDTO(updating);
        }
    }

    @Override
    public boolean exist(String id) {
        return conn.existsById(id);
    }

    @Override
    public void deletePerson(String id) {
        conn.deleteById(id);
    }

    @Override
    public long count() {
        return conn.count();
    }

    @Override
    public void deleteAllPersons() {
        conn.deleteAll();
    }
}
