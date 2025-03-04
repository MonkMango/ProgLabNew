package se.hig.service;

import se.hig.db.DbConnectionManager;
import se.hig.domain.Person;
import se.hig.repository.DaoFactory;
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

    private final DaoFactory factory;

    public PersonService() {this(new DaoFactory());}

    public PersonService(DaoFactory factory) {
        this.factory = factory;
    }

    public List<Person> getAllPersons() throws CleaningManagerServiceException {
        List<Person> personList;
        try {
            DbConnectionManager.getInstance().open();
            personList = factory.getPersonDao().getAll();
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve all persons", e);
        } finally {
            DbConnectionManager.getInstance().close();
        }
        return personList;
    }

    public Person getPerson(int id) throws CleaningManagerServiceException {

        Person person;
        try {
            DbConnectionManager.getInstance().open();
            person = factory.getPersonDao().get(id);

        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve person with ID: " + id, e);
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
        return person;
    }

    public Person savePerson(Person person) throws CleaningManagerServiceException {
        Person savedPerson;
        try {
            DbConnectionManager.getInstance().open();
            savedPerson = factory.getPersonDao().save(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to save person", e);
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
        return savedPerson;
    }

    public Person updatePerson(Person person) throws CleaningManagerServiceException {
        Person updatedPerson;
        try {
            DbConnectionManager.getInstance().open();
            updatedPerson = factory.getPersonDao().update(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to update person", e);
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
        return updatedPerson;
    }

    public Person deletePerson(Person person) throws CleaningManagerServiceException {
        Person deletedPerson;
        try {
            DbConnectionManager.getInstance().open();
            deletedPerson = factory.getPersonDao().delete(person);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to delete person", e);
        }
        finally {
            DbConnectionManager.getInstance().close();
        }
        return deletedPerson;
    }


}
