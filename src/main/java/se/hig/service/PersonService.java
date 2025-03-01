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

    private final PersonDao personDao;

    public PersonService() {this(new PersonDao());}

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> getAllPersons() throws CleaningManagerServiceException {
        try {
            personDao.openConnection();
            List<Person> personList = personDao.getAll();
            personDao.closeConnection();
            return personList;
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve all persons", e);
        }
    }

    public Person getPerson(int id) throws CleaningManagerServiceException {
        try {
            personDao.openConnection();
            Person person = personDao.get(id);
            personDao.closeConnection();
            return person;
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve person with ID: " + id, e);
        }
    }

    public Person savePerson(Person person) throws CleaningManagerServiceException {
        try {
            personDao.openConnection();
            Person savedPerson = personDao.save(person);
            personDao.closeConnection();
            return savedPerson;
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to save person", e);
        }
    }

    public Person updatePerson(Person person) throws CleaningManagerServiceException {
        try {
            personDao.openConnection();
            Person updatedPerson = personDao.update(person);
            personDao.closeConnection();
            return updatedPerson;
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to update person", e);
        }
    }

    public Person deletePerson(Person person) throws CleaningManagerServiceException {
        try {
            personDao.openConnection();
            Person deletedPerson = personDao.delete(person);
            personDao.closeConnection();
            return deletedPerson;
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to delete person", e);
        }
    }


}
