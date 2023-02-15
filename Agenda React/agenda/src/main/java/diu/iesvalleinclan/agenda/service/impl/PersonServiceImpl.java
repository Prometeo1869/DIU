package diu.iesvalleinclan.agenda.service.impl;

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
        return null;
    }

    @Override
    public PersonDTO getById(Integer id) {
        return null;
    }

    @Override
    public PersonDTO updatePerson(Integer id, PersonDTO dto) {
        return null;
    }

    @Override
    public boolean exist(Integer id) {
        return false;
    }

    @Override
    public void deletePerson(Integer id) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteAllPersons() {

    }
}
