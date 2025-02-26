package java;

import se.hig.domain.Branch;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BranchTest {

    Branch b;
    String nullName;
    String emptyName;
    String longName;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        b = new Branch("Bada Bing", "Newark");
        nullName = null;
        emptyName = "";
        longName = "Bada Bada Bada Bada Bada Bada Bada Bada Bada Bing";
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        b = null;
    }

    @org.junit.jupiter.api.Test
    void testSetNullName() {
        assertThrows(IllegalArgumentException.class, () -> b.setName(nullName));
    }

    @org.junit.jupiter.api.Test
    void testSetEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> b.setName(emptyName));
    }

    @org.junit.jupiter.api.Test
    void testSetLongName() {
        assertThrows(IllegalArgumentException.class, () -> b.setName(longName));
    }

    @org.junit.jupiter.api.Test
    void testSetNullCity() {
        assertThrows(IllegalArgumentException.class, () -> b.setCity(nullName));
    }

    @org.junit.jupiter.api.Test
    void testSetEmptyCity() {
        assertThrows(IllegalArgumentException.class, () -> b.setCity(emptyName));
    }

    @org.junit.jupiter.api.Test
    void testSetLongCity() {
        assertThrows(IllegalArgumentException.class, () -> b.setCity(longName));
    }
}