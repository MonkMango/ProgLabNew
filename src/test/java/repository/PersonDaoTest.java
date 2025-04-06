package repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.PersonDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonDaoTest {

    private PersonDao personDao;
    private DbConnectionManager dbConnectionMock;
    private ResultSet resultSetMock;
    private PreparedStatement preparedStatementMock;

    @BeforeEach
    void setUp() throws Exception {
        dbConnectionMock = mock(DbConnectionManager.class);
        resultSetMock = mock(ResultSet.class);
        preparedStatementMock = mock(PreparedStatement.class);

        when(dbConnectionMock.excecuteQuery(anyString())).thenReturn(resultSetMock);
        when(dbConnectionMock.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatementMock);
        when(dbConnectionMock.prepareReturnStatement(anyString(), anyInt())).thenReturn(preparedStatementMock);

        personDao = new PersonDao(dbConnectionMock);
    }

    @Test
    void getPersonByIdSuccess() throws Exception {

        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(1);
        when(resultSetMock.getString(2)).thenReturn("Johnny Sack");
        when(resultSetMock.getInt(3)).thenReturn(1995);


        Branch testBranch = new Branch(1, "Pizza Land", "New York");
        when(resultSetMock.getObject(4)).thenReturn(testBranch);


        Person person = personDao.get(1).orElseThrow();


        assertNotNull(person);
        assertEquals(1, person.getId());
        assertEquals("Johnny Sack", person.getName());
        assertEquals(1995, person.getBirthYear());
        assertEquals(testBranch, person.getBranch());


        verify(dbConnectionMock, times(1)).excecuteQuery("SELECT id, name, birth_year, branch FROM lab_persons WHERE id=1");
    }


    @Test
    void getPersonByIdFail() throws Exception {
        when(resultSetMock.next()).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> personDao.get(100));

        verify(dbConnectionMock, times(1)).excecuteQuery(anyString());
    }

    @Test
    void savePerson() throws Exception {

        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        when(preparedStatementMock.getGeneratedKeys()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt(1)).thenReturn(1);


        Branch testBranch = new Branch(1, "Sing Sing", "New York");
        Person newPerson = new Person("Feech LaMana", 1962, testBranch);

        Person savedPerson = personDao.save(newPerson).orElseThrow();

        assertNotNull(savedPerson);
        assertEquals(1, savedPerson.getId());
        assertEquals("Feech LaMana", savedPerson.getName());
        assertEquals(1962, savedPerson.getBirthYear());

        verify(preparedStatementMock, times(1)).executeUpdate();
    }

}
