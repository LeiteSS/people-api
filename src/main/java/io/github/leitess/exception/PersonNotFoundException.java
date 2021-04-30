package io.github.leitess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long id) {
        super("Person not found with ID " + id);
    }

    public PersonNotFoundException(String personName) {
        super(String.format("The person %s was not found.", personName));
    }
}
