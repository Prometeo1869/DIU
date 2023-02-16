package diu.iesvalleinclan.agenda.util;

import diu.iesvalleinclan.agenda.model.Person;
import diu.iesvalleinclan.agenda.model.dto.PersonDTO;
import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonConvert {

    private final ModelMapper mapper;

    public PersonDTO convertToDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    public Person convertTo(PersonDTO dto) {
        return mapper.map(dto, Person.class);
    }

}
