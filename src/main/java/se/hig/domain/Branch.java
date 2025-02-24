package se.hig.domain;

/**
 * Representing a specific branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class Branch {

    private int id;
    private String name;
    private String city;

    public Branch(int id, String name, String city) {
        this(name, city);
        setId(id);
    }

    public Branch(String name, String city) {
        setName(name);
        setCity(city);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if(name.length() > 45) {
            throw new IllegalArgumentException("Name cannot exceed 45 characters");
        }

        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.length() == 0) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }

        if(city.length() > 45) {
            throw new IllegalArgumentException("City cannot exceed 45 characters");
        }

        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, City: %s", id, name, city);
    }
}
