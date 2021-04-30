package io.github.leitess.repository;

import io.github.leitess.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByFirstName(String firstName);

    Optional<Person> findByLastName(String LastName);
}
