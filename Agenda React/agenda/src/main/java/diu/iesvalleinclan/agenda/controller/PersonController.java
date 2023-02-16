package diu.iesvalleinclan.agenda.controller;

import diu.iesvalleinclan.agenda.model.dto.PersonDTO;
import diu.iesvalleinclan.agenda.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createdPerson(person));
    }

    @GetMapping("/persons/{lastName}")
    public ResponseEntity<?> findByTitleContaining(@PathVariable String lastName) {
        List<PersonDTO> lista = service.getByLastName(lastName);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/persons")
    public ResponseEntity<?> findAll() {
        List<PersonDTO> lista = service.getPersons();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
        PersonDTO dto = service.getById(id);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody PersonDTO dto) {
        PersonDTO updating = service.updatePerson(id, dto);
        if (updating == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updating);
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Integer id) {
        if (service.exist(id)) {
            service.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/persons")
    public ResponseEntity<?> deleteAllPersons() {
        if (service.count() > 0) {
            service.deleteAllPersons();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
