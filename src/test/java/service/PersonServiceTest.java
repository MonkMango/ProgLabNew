package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.PersonDao;
import se.hig.service.PersonService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;


class PersonServiceTest {

    PersonService personService;
    PersonDao personDaoMock;
    Branch testBranch = new Branch(1, "Bada Bong", "Newark");
    Person testPerson = new Person(1, "Tony Soprano", 1999, testBranch);

    @BeforeEach
    void setUp() {

        personDaoMock = mock(PersonDao.class);
        when(personDaoMock.getAll()).thenReturn(List.of(testPerson));
        when(personDaoMock.get(1)).thenReturn(testPerson);

        personService = new PersonService(personDaoMock);
    }

    @AfterEach
    void tearDown() {
        personService = null;
        personDaoMock = null;
    }

    @Test
    void getAllPersons() {
        List<Person> result = personService.getAllPersons();
        verify(personDaoMock, times(1)).getAll();
        assertTrue(result.get(0) instanceof Person);
        assertEquals("Boris", result.get(0).getName());
    }

    @Test
    void getPerson() throws SQLException {
        Person result = personService.getPerson(1);
        //verify(personDaoMock, times(1));
        verify(personDaoMock, times(1)).get(1);
        assertEquals("Boris", result.getName());
    }
}

