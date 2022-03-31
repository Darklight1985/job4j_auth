package ru.job4j.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
