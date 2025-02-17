package se.hig.repository;


import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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


    public Branch get(int id) throws NoSuchElementException {
        Branch branch = null;
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, city FROM lab_branches WHERE id=" + id);
            if (!resultSet.next())
                throw new NoSuchElementException("The branch with id " + id + " doesn't exist in database");
            else
                branch = new Branch(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branch;
    }

    public List<Branch> getAll() {

        ArrayList<Branch> list = new ArrayList<>();

        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, city FROM lab_branches");
            while (resultSet.next()) {
                list.add(new Branch(resultSet.getInt(1),
                        resultSet.getString(2).trim(),
                        resultSet.getString(3))
                );

            }
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Branch save(Branch t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        boolean saveSucess = false;
        Branch savedBranch = null;

        try {


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


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedBranch;
    }

    public Branch update(Branch t) {
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        if (t == null || t.getId() <= 0) {
            return null;
        }

        try {
            preparedStatement = dbConManagerSingleton.prepareStatement(
                    "UPDATE lab_branches SET name = ?, city = ? WHERE id = ?",
                    Statement.NO_GENERATED_KEYS
            );
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getCity());
            preparedStatement.setInt(3, t.getId());

            rowsAffected = preparedStatement.executeUpdate();


            if (rowsAffected > 0) {
                return new Branch(t.getId(), t.getName(), t.getCity());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Branch delete(Branch t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Branch deletedBranch = null;

        if (t == null) {
            return null;
        }

        int branchId = t.getId();

        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedBranch;
    }

}