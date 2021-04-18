package lab.aulaDIO.personApi.services;

import lab.aulaDIO.personApi.exception.PersonNotFoundException;
import lab.aulaDIO.personApi.utils.PersonUtils;
import lab.aulaDIO.personApi.mapper.PersonMapper;
import lab.aulaDIO.personApi.dto.request.PersonDTO;
import lab.aulaDIO.personApi.dto.response.MessageResponseDTO;
import lab.aulaDIO.personApi.entity.Person;
import lab.aulaDIO.personApi.repository.PersonRepository;
import lab.aulaDIO.personApi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static lab.aulaDIO.personApi.utils.PersonUtils.createFakeDTO;
import static lab.aulaDIO.personApi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
//        given
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();
//        when
        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO successMessage = personService.createPerson(personDTO);
//        then
        assertEquals("Created person with ID 1", successMessage.getMessage());
    }

    @Test
    void testGivenValidPersonIdThenReturnThisPerson() throws PersonNotFoundException {
//        given
        PersonDTO expectedPersonDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();
        expectedPersonDTO.setId(expectedSavedPerson.getId());
//        when
        when(personRepository.findById(expectedSavedPerson.getId())).thenReturn(Optional.of(expectedSavedPerson));
        when(personMapper.toDTO(expectedSavedPerson)).thenReturn(expectedPersonDTO);

        PersonDTO personDTO = personService.findById(expectedSavedPerson.getId());
//        then
        assertEquals(expectedPersonDTO, personDTO);

        assertEquals(expectedSavedPerson.getId(), personDTO.getId());
        assertEquals(expectedSavedPerson.getFirstName(), personDTO.getFirstName());
    }

    @Test
    void testGivenInvalidPersonIdThenThrowException() {
//        given
        var invalidPersonId = 1L;
//        when
        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));
//        then
        assertThrows(PersonNotFoundException.class, () -> personService.findById(invalidPersonId));
    }

    @Test
    void testGivenNoDataThenReturnAllPeopleRegistered() {
//        given
        List<Person> expectedRegisteredPeople = Collections.singletonList(createFakeEntity());
        PersonDTO personDTO = createFakeDTO();
//        when
        when(personRepository.findAll()).thenReturn(expectedRegisteredPeople);
        when(personMapper.toDTO(any(Person.class))).thenReturn(personDTO);

        List<PersonDTO> expectedPeopleDTOList = personService.listAll();
//        then
        assertFalse(expectedPeopleDTOList.isEmpty());
        assertEquals(expectedPeopleDTOList.get(0).getId(), personDTO.getId());
    }

    @Test
    void testGivenValidPersonIdAndUpdateInfoThenReturnSuccesOnUpdate() throws PersonNotFoundException {
//        given
        var updatedPersonId = 2L;

        PersonDTO updatePersonDTORequest = createFakeDTO();
        updatePersonDTORequest.setId(updatedPersonId);
        updatePersonDTORequest.setLastName("Leite updated");

        Person expectedPersonToUpdate = createFakeEntity();
        expectedPersonToUpdate.setId(updatedPersonId);

        Person expectedPersonUpdated = createFakeEntity();
        expectedPersonUpdated.setId(updatedPersonId);
        expectedPersonToUpdate.setLastName(updatePersonDTORequest.getLastName());
//        when
        when(personRepository.findById(updatedPersonId)).thenReturn(Optional.of(expectedPersonUpdated));
        when(personMapper.toModel(updatePersonDTORequest)).thenReturn(expectedPersonUpdated);
        when(personRepository.save(any(Person.class))).thenReturn(expectedPersonUpdated);

        MessageResponseDTO successMessage = personService.updateById(updatedPersonId, updatePersonDTORequest);
//        then
        assertEquals("Updated person with ID 2", successMessage.getMessage());
    }

    @Test
    void testGivenInvalidPersonIdAndUpdateInfoThenThrowExceptionOnUpdate() throws PersonNotFoundException {
//        given
        var invalidPersonId = 1L;

        PersonDTO updatePersonDTORequest = createFakeDTO();
        updatePersonDTORequest.setId(invalidPersonId);
        updatePersonDTORequest.setLastName("Leite updated");

//        when
        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));
//        then
        assertThrows(PersonNotFoundException.class, () -> personService.updateById(invalidPersonId, updatePersonDTORequest));
    }

    @Test
    void testGivenValidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
//        given
        var deletedPersonId = 1L;
        Person expectedPersonToDelete = createFakeEntity();
//        when
        when(personRepository.findById(deletedPersonId)).thenReturn(Optional.of(expectedPersonToDelete));
        personService.deleteById(deletedPersonId);
//        then
        verify(personRepository, times(1)).deleteById(deletedPersonId);
    }

    @Test
    void testGivenInvalidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
//        given
        var invalidPersonId = 1L;
//        when
        when(personRepository.findById(invalidPersonId))
                .thenReturn(Optional.ofNullable(any(Person.class)));

//        then
        assertThrows(PersonNotFoundException.class, () -> personService.deleteById(invalidPersonId));
    }

}
