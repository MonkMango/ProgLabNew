

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.domain.Person;
import se.hig.repository.PersonDao;
import se.hig.service.PersonService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;


class PersonServiceTest {

    PersonService personService;
    PersonDao personDaoMock;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
        personDaoMock = mock(PersonDao.class);
        when(personDaoMock.getAll()).thenReturn(List.of(new Person(1, "Boris", 1999)));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPersons() {
        List<Person> result = personService.getAllPersons();
        //verify(personDaoMock, times(1));
        assertTrue(result.get(0) instanceof Person);
        assertEquals("Boris", result.get(0).getName());
    }

    @Test
    void getPerson() {
    }
}

