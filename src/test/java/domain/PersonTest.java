package java.domain;

import se.hig.domain.Branch;
import se.hig.domain.Person;

import static org.junit.jupiter.api.Assertions.*;
class PersonTest {

    Person p;
    Branch b;
    String nullName;
    String emptyName;
    String longName;
    int pastYear;
    int futureYear;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p = new Person("Bada Bing", 1984, b);
        nullName = null;
        emptyName = "";
        longName = "Bada Bada Bada Bada Bada Bada Bada Bada Bada Bing";
        pastYear = 1784;
        futureYear = 2084;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        p = null;
    }

    @org.junit.jupiter.api.Test
    void testSetNullName() {
        assertThrows(IllegalArgumentException.class, () -> p.setName(nullName));
    }

    @org.junit.jupiter.api.Test
    void testSetEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> p.setName(emptyName));
    }

    @org.junit.jupiter.api.Test
    void testSetLongName() {
        assertThrows(IllegalArgumentException.class, () -> p.setName(longName));
    }

    @org.junit.jupiter.api.Test
    void testSetPastYear() {
        assertThrows(IllegalArgumentException.class, () -> p.setBirthYear(pastYear));
    }

    @org.junit.jupiter.api.Test
    void testSetFutureYear() {
        assertThrows(IllegalArgumentException.class, () -> p.setBirthYear(futureYear));
    }

}