package se.hig.main;

import se.hig.domain.Person;
import se.hig.repository.PersonDao;
import se.hig.service.PersonService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main method demoing the different layers.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class Spike {
    public static void main(String[] args) throws SQLException {

        List<Person> peopleList = new ArrayList<>();
        PersonService personService = new PersonService(new PersonDao());
        peopleList = personService.getAllPersons();

        System.out.println("List of people: ");
        for (Person person : peopleList) {
            System.out.println(person);
        }

        System.out.println("Person with ID 1: ");
        System.out.println(personService.getPerson(1));


        System.out.println("Adding person:");

        Person newPerson = new Person("Klaus Kinski", 1964);
        Person completePerson = personService.savePerson(newPerson);
        System.out.println(completePerson);

        System.out.println("Updating person: ");

        completePerson.setName("Al Pacino");
        System.out.println(personService.updatePerson(completePerson));

        System.out.println("Deleting person: ");
        System.out.println(personService.deletePerson(completePerson));

    }
}
