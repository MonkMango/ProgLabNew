package se.hig.service;

import se.hig.domain.Person;
import se.hig.repository.PersonDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Service object handling all operations for the Person class.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class PersonService {

   PersonDao personDao;

   public PersonService() {this(new PersonDao());}

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPersons() {
        return personDao.getAll();
    }

    public Person getPerson(int id) throws SQLException {
      return personDao.get(id);
    }

    public Person savePerson(Person person) throws SQLException {
        return personDao.save(person);
    }

    public Person updatePerson(Person person) {
        return personDao.update(person);
    }

    public Person deletePerson(Person person) {
        return personDao.delete(person);
    }

}
