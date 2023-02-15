package diu.iesvalleinclan.agenda.repository;

import diu.iesvalleinclan.agenda.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface Connection extends MongoRepository<Person, Integer> {
    Arrays findByLastName(String lastName);
}
