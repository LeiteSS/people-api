package lab.aulaDIO.personApi.service;

import lab.aulaDIO.personApi.dto.request.PersonDTO;
import lab.aulaDIO.personApi.dto.response.MessageResponseDTO;
import lab.aulaDIO.personApi.entity.Person;
import lab.aulaDIO.personApi.mapper.PersonMapper;
import lab.aulaDIO.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created Person with ID " + savedPerson.getId())
                .build();
    }
}
