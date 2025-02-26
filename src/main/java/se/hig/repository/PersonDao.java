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

/**
 * DAO for the persistent handling of a Person object. It manages all
 * CRUD operations and conversion between the object world student and
 * the relational version student (DB version).
 * Due to the use of a DbConnectionManager the DAO doesen't need to 
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in 
 * relational sql strings, tables, columns and result sets with the database.
 * @author awi
 *
 */
public class PersonDao implements Dao<Person> {

	DbConnectionManager dbConManagerSingleton = null;
	
	public PersonDao() {
		this(DbConnectionManager.getInstance());
	}

	public PersonDao(DbConnectionManager dbConnMan) {
		dbConManagerSingleton = dbConnMan;
	}
	
	
	public Person get(int id) throws NoSuchElementException {
		Person student = null;
		try{
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, branch FROM lab_persons WHERE id=" + id);
			if( !resultSet.next())
				throw new NoSuchElementException("The person with id " + id + " doesen't exist in database");
			else
				student = new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), (Branch) resultSet.getObject(4));
			dbConManagerSingleton.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}

	public List<Person> getAll() {
		
		ArrayList<Person> list = new ArrayList<>();
		
		try {
			ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year, branch FROM lab_persons");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getInt(1), 
									 resultSet.getString(2).trim(),
									 resultSet.getInt(3),
						(Branch) resultSet.getObject(4))
						);
				
			}
			dbConManagerSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Person save(Person t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowCount = 0;
		boolean saveSucess = false;
		Person savedPerson = null;

		try {

			
			//*******This is the main 'save' operation ***************************
			preparedStatement = dbConManagerSingleton.prepareReturnStatement(
											  "INSERT INTO lab_persons (name, birth_year, branch) " +
											  "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getBirthYear());
			preparedStatement.setObject(3, t.getBranch());

			preparedStatement.executeUpdate();
			// ********************************************************************



				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					int generatedId = resultSet.getInt(1);

					savedPerson = new Person(generatedId, t.getName(), t.getBirthYear(), t.getBranch());
				}


		}
		catch ( SQLException e) {
			e.printStackTrace();
		}
		return savedPerson;
	}
	/**
	 * This method uses a temporary Student set with the desired changed values.
	 * It must have a 'id' that corresponds to a existing record in the database.
	 * @param t - an instance of a Student with new values on attributes but 
	 * an 'id' identical to an existing student in the DB
	 */
	public Person update(Person t) {
		PreparedStatement preparedStatement = null;
		int rowsAffected = 0;

		if (t == null || t.getId() <= 0) {
			return null;
		}

		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"UPDATE lab_persons SET name = ?, birth_year = ?, branch = ? WHERE id = ?",
					Statement.NO_GENERATED_KEYS
			);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getBirthYear());
			preparedStatement.setInt(3, t.getId());
			preparedStatement.setObject(4, t.getBranch());

			rowsAffected = preparedStatement.executeUpdate();


			if (rowsAffected > 0) {
				return new Person(t.getId(), t.getName(), t.getBirthYear(), t.getBranch());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	public Person delete(Person t) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person deletedPerson = null;

		if (t == null) {
			return null;
		}

		int personId = t.getId();

		try {
			preparedStatement = dbConManagerSingleton.prepareStatement(
					"SELECT * FROM lab_persons WHERE id = ?", Statement.NO_GENERATED_KEYS
			);
			preparedStatement.setInt(1, personId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				int birthYear = resultSet.getInt("birth_year");
				Branch branch = (Branch) resultSet.getObject("branch");


				deletedPerson = new Person(personId, name, birthYear, branch);
			}


			if (deletedPerson != null) {
				preparedStatement = dbConManagerSingleton.prepareStatement(
						"DELETE FROM lab_persons WHERE id = ?", Statement.NO_GENERATED_KEYS
				);
				preparedStatement.setInt(1, personId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletedPerson;
	}


}
