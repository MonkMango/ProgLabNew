package se.hig.repository;


import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.domain.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Data Access Object for the Branch class.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class BranchDao implements Dao<Branch> {

    DbConnectionManager dbConManagerSingleton = null;

    public BranchDao() {
        dbConManagerSingleton = DbConnectionManager.getInstance();
    }


    public Optional<Branch> get(int id) throws NoSuchElementException, SQLException {
        Branch branch = null;
         {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, city FROM lab_branches WHERE id=" + id);
            if (!resultSet.next())
                throw new NoSuchElementException("The branch with id " + id + " doesn't exist in database");
            else
                branch = new Branch(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }

        return Optional.ofNullable(branch);
    }

    public List<Branch> getAll() throws SQLException {

        ArrayList<Branch> list = new ArrayList<>();

         {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, city FROM lab_branches");
            while (resultSet.next()) {
                list.add(new Branch(resultSet.getInt(1),
                        resultSet.getString(2).trim(),
                        resultSet.getString(3))
                );

            }
        }
        return list;
    }

    public Optional<Branch> save(Branch t) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        boolean saveSucess = false;
        Branch savedBranch = null;

         {


            preparedStatement = dbConManagerSingleton.prepareReturnStatement(
                    "INSERT INTO lab_branches (name, city) " +
                            "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getCity());

            preparedStatement.executeUpdate();


            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);


                savedBranch = new Branch(generatedId, t.getName(), t.getCity());
            }


        }
        return Optional.ofNullable(savedBranch);
    }

    public Optional<Branch> update(Branch t) throws SQLException {
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        if (t == null || t.getId() <= 0) {
            return null;
        }

         {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE lab_branches SET name = ?, city = ? WHERE id = ?",
                    Statement.NO_GENERATED_KEYS
            );
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getCity());
            preparedStatement.setInt(3, t.getId());

            rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                return Optional.ofNullable(new Branch(t.getId(), t.getName(), t.getCity()));
            }
        }

        return Optional.empty();
    }


    public Optional<Branch> delete(Branch t) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Branch deletedBranch = null;

        if (t == null) {
            return Optional.empty();
        }

        int branchId = t.getId();

         {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "SELECT * FROM lab_branches WHERE id = ?", Statement.NO_GENERATED_KEYS
            );
            preparedStatement.setInt(1, branchId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");


                deletedBranch = new Branch(branchId, name, city);
            }


            if (deletedBranch != null) {
                preparedStatement = dbConManagerSingleton.prepareStatement(
                        "DELETE FROM lab_branches WHERE id = ?", Statement.NO_GENERATED_KEYS
                );
                preparedStatement.setInt(1, branchId);
                preparedStatement.executeUpdate();
            }
        }
        return Optional.ofNullable(deletedBranch);
    }

    public List<Person> getPersonsByBranchId(int branchId) throws SQLException {
        List<Person> persons = new ArrayList<>();

         {
            String query = "SELECT id, name, birthYear FROM persons WHERE branch_id = ?";
            PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, branchId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                int birthYear = resultSet.getInt("birthYear");

                // Create a Person object with the branch
                Person person = new Person(id, name, birthYear, get(branchId).orElseThrow());
                persons.add(person);
            }
        }

        return persons;
    }


    public void openConnection(){
        dbConManagerSingleton.open();
    }

    public void closeConnection(){
        dbConManagerSingleton.close();
    }

}