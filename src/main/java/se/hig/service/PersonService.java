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

        Person person;
        try {
            personDao.openConnection();
            person = personDao.get(id);

        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve person with ID: " + id, e);
        }
        finally {
            personDao.closeConnection();
        }
        return person;
    }

    public Person savePerson(Person person) throws CleaningManagerServiceException {
        Person savedPerson;
        try {
            personDao.openConnection();
            savedPerson = personDao.save(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to save person", e);
        }
        finally {
            personDao.closeConnection();
        }
        return savedPerson;
    }

    public Person updatePerson(Person person) throws CleaningManagerServiceException {
        Person updatedPerson;
        try {
            personDao.openConnection();
            updatedPerson = personDao.update(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to update person", e);
        }
        finally {
            personDao.closeConnection();
        }
        return updatedPerson;
    }

    public Person deletePerson(Person person) throws CleaningManagerServiceException {
        Person deletedPerson;
        try {
            personDao.openConnection();
            deletedPerson = personDao.delete(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to delete person", e);
        }
        finally {
            personDao.closeConnection();
        }
        return deletedPerson;
    }


}
