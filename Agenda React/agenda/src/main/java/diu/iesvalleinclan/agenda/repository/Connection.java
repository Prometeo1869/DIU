package diu.iesvalleinclan.agenda.repository;

import diu.iesvalleinclan.agenda.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Connection extends MongoRepository<Person, String> {
    List<Person> findByLastName(String lastName);
}
