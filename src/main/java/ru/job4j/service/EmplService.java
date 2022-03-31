package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeeRepository;
import ru.job4j.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmplService {
    private EmployeeRepository employeeRepository;
    private PersonRepository personRepository;

    public EmplService(EmployeeRepository employeeRepository, PersonRepository personRepository) {
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
    }

    public Collection<Employee> findAll() {
        List<Employee> res = new ArrayList<>();
        employeeRepository.findAll().forEach(res::add);
        return res;
    }

    public Employee findById(int id) {
        Employee employee = employeeRepository.findById(id).get();
        List<Person> persons = (List<Person>) personRepository.findAll();
        for (Person person : persons) {
            if (person.getEmplId() == employee.getId()) {
                employee.addList(person);
            }
        }
        return employee;
    }
}
