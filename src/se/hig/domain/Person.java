package se.hig.domain;

import java.time.Year;

/**
 * An object modelling a person.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class Person {

    private int id;
    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        setName(name);
        setBirthYear(birthYear);
    }

    public Person(int id, String name, int birthYear) {
        this(name, birthYear);
        setId(id);
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        if(birthYear < Year.now().getValue() -75 || birthYear > Year.now().getValue() - 16) {
            throw new IllegalArgumentException("Birth year must be between 1800 and 2020");
        }
        else {
            this.birthYear = birthYear;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if(name.length() > 45) {
            throw new IllegalArgumentException("Name is too long");
        }

        this.name = name;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, Birth Year: %d", id, name, birthYear);
    }

    public int getId() {
        return id;
    }
}
