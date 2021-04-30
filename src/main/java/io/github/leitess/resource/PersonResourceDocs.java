package io.github.leitess.resource;

import io.github.leitess.exception.PersonNotFoundException;
import io.github.leitess.resource.dto.request.PersonDTO;
import io.github.leitess.resource.dto.response.MessageResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api("Manage People")
public interface PersonResourceDocs {

    @ApiOperation(value = "Register a Person")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person Created successfully."),
            @ApiResponse(code = 400, message = "Field empty or invalid")
    })
    MessageResponseDTO createPerson(PersonDTO PersonDTO);

    @ApiOperation(value = "Find a Person using its firstname.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person found."),
            @ApiResponse(code = 404, message = "Person NOT Found.")
    })
    PersonDTO findByFirstName(String firstName) throws PersonNotFoundException;

    @ApiOperation(value = "Find a Person using its lastname.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person found."),
            @ApiResponse(code = 404, message = "Person NOT Found.")
    })
    PersonDTO findByLastName(String LastName) throws PersonNotFoundException;

    @ApiOperation(value = "List all People.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all people registered in the system"),
    })
    List<PersonDTO> listAll();

    @ApiOperation(value = "Update a Person By Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person updated successfully."),
            @ApiResponse(code = 400, message = "Fields empty or invalid")
    })
    MessageResponseDTO updateById(Long id, PersonDTO PersonDTO) throws PersonNotFoundException;

    @ApiOperation(value = "Delete a Person using its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Person deleted successfully from the system."),
            @ApiResponse(code = 404, message = "Person not found.")
    })
    void deleteById(Long id) throws PersonNotFoundException;
}
