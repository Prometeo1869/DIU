package diu.iesvalleinclan.agenda.controller;

import diu.iesvalleinclan.agenda.model.dto.PersonDTO;
import diu.iesvalleinclan.agenda.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createdPerson(person));
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        PersonDTO dto = service.getById(id);

        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping("/persons/name/{lastName}")
    public ResponseEntity<?> findByTitleContaining(@PathVariable String lastName) {
        List<PersonDTO> lista = service.getByLastName(lastName);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/persons")
    public ResponseEntity<?> findAll() {
        List<PersonDTO> lista = service.getPersons();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/persons_nameorder")
    public ResponseEntity<?> findSortFirstName() {
        List<PersonDTO> lista = service.getPersons();
        lista.sort(new Comparator<PersonDTO>() {
            @Override
            public int compare(PersonDTO o1, PersonDTO o2) {
                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            }
        });

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/persons_lastname")
    public ResponseEntity<?> findSortLastName() {
        List<PersonDTO> lista = service.getPersons();
        lista.sort(new Comparator<PersonDTO>() {
            @Override
            public int compare(PersonDTO o1, PersonDTO o2) {
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable String id, @RequestBody PersonDTO dto) {
        PersonDTO updating = service.updatePerson(id, dto);
        if (updating == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updating);
        }
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable String id) {
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

    /* POST EJEMPLO
            {
	            "id" : "1",
  	            "firstName" : "Juan",
              	"lastName" : "Cebrian",
  	            "street" : "Urquiza",
  	            "postalCode" : 41003,
  	            "city" : "Sevilla",
  	            "birthday" : "1984-08-16"
            }
     */

}
