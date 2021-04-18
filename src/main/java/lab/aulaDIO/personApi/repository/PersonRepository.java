package lab.aulaDIO.personApi.repository;

import lab.aulaDIO.personApi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
