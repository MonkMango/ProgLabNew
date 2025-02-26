package java.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.domain.Person;
import se.hig.repository.PersonDao;
import se.hig.service.PersonService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PersonServiceTest2 {

    private PersonDao personDao;

    @BeforeEach
    void setUp() {
        personDao = new PersonDao();
    }

    @AfterEach
    void tearDown() {
        personDao = null;
    }

    @Test
    void testSave() {

    }

    @Test
    void testGet() {

    }

    @Test
    void testGetAll() {
        System.out.println("getAll:");
        PersonDao personDaoMock = mock(PersonDao.class);
//        Employee kalle = new Employee(1, "Kalle Kaka", "städare");
        when(personDaoMock.getAll()).thenReturn(List.of(new Person(1, "Kalle Kaka", 1988)));
        PersonService instance = new PersonService(personDaoMock);
//        List<Employee> expResult = null;
        List<Person> result = instance.getAllPersons();
        assertTrue(result instanceof List<Person>);
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof Person);
        assertTrue(new Person(1, "Kalle Kaka", 1988).equals(result.getFirst()));

        verify(personDaoMock, times(1)).getAll();
    }

    @Test
    void testUpdate() {

    }

    @Test
    void testDelete() {

    }
}




