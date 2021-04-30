package io.github.leitess.service;

import io.github.leitess.entity.Person;
import io.github.leitess.exception.PersonNotFoundException;
import io.github.leitess.resource.dto.request.PersonDTO;
import io.github.leitess.resource.dto.response.MessageResponseDTO;
import io.github.leitess.mapper.PersonMapper;
import io.github.leitess.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findByFirstName(String firstName) throws PersonNotFoundException {
        Person personFounded = verifyFirstName(firstName);

        return personMapper.toDTO(personFounded);
    }

    public PersonDTO findByLastName(String lastName) throws PersonNotFoundException {
        Person personFounded = verifyLastName(lastName);

        return personMapper.toDTO(personFounded);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private Person verifyFirstName(String firstName) throws PersonNotFoundException {
        return personRepository
                .findByFirstName(firstName)
                .orElseThrow(() -> new PersonNotFoundException(firstName));
    }

    private Person verifyLastName(String lastName) throws PersonNotFoundException {
        return personRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new PersonNotFoundException(lastName));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
