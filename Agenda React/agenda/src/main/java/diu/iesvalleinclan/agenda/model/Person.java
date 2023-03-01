package diu.iesvalleinclan.agenda.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "agenda")
@JsonPropertyOrder({"id", "firstName", "lastName", "street", "postalCode", "city", "birthday"})
@Data
public class Person {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String street;
    private Integer postalCode;
    private String city;
    private LocalDate birthday;
}
