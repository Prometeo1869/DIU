package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;

import java.sql.Date;
import java.time.LocalDate;


public class PersonConvert {

    public static Person convertToPerson(PersonVO pvo) {
           Person p = new Person();
           p.setFirstName(pvo.getFirstName());
           p.setLastName(pvo.getLastName());
           p.setId(pvo.getCode());
           p.setStreet(pvo.getStreet());
           p.setPostalCode(pvo.getPostalCode());
           p.setCity(pvo.getCity());
           Date fecha = pvo.getBirthday();
           p.setBirthday(fecha.toLocalDate());

        return p;
    }

    public static PersonVO convertToPersonVO(Person p) {
        PersonVO pvo = new PersonVO();
        pvo.setCode(p.getId());
        pvo.setFirstName(p.getFirstName());
        pvo.setLastName(p.getLastName());
        pvo.setStreet(p.getStreet());
        pvo.setPostalCode(p.getPostalCode());
        pvo.setCity(p.getCity());
        LocalDate fecha = p.getBirthday();
        pvo.setBirthday(Date.valueOf(fecha));
        return pvo;
    }
}
