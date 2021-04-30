package io.github.leitess.resource;

import io.github.leitess.resource.dto.request.PersonDTO;
import io.github.leitess.resource.dto.response.MessageResponseDTO;
import io.github.leitess.exception.PersonNotFoundException;
import io.github.leitess.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonResource implements PersonResourceDocs{

    private final PersonService personService;

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping("/people/firstName/{firstName}")
    public PersonDTO findByFirstName(@PathVariable String firstName) throws PersonNotFoundException {
        return personService.findByFirstName(firstName);
    }

    @GetMapping("/people/lastName/{lastName}")
    public PersonDTO findByLastName(@PathVariable String lastName) throws PersonNotFoundException {
        return personService.findByLastName(lastName);
    }

    @GetMapping("/people")
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @PutMapping("/people/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/people/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }
}
