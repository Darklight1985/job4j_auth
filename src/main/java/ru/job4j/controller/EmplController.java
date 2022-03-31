package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeeRepository;
import ru.job4j.repository.PersonRepository;
import ru.job4j.service.EmplService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/employee")
public class EmplController {

    private static final String API = "http://localhost:8080/person/";

    private static final String API_ID = "http://localhost:8080/person/{id}";

    private final EmplService empls;

    @Autowired
    private RestTemplate rest;

    public EmplController(EmplService empls) {
        this.empls = empls;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> rsl = (List<Employee>) empls.findAll();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
                }).getBody();
        for (Employee employee : rsl) {
            for (Person person : persons) {
                if (person.getEmplId() == employee.getId()) {
                    employee.addList(person);
                }
            }
        }
    return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Person person) {
        var employee = this.empls.findById(person.getEmplId());
        if (isNull(employee)) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            employee.addList(rest.postForObject(API, person, Person.class));
            return new ResponseEntity<>(
                    employee,
                    HttpStatus.ACCEPTED
            );
        }
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        var employee = this.empls.findById(person.getEmplId());
        if (isNull(employee)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            rest.put(API, rest.postForObject(API, person, Person.class));
            employee.addList(person);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}

