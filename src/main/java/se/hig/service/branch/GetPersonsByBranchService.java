package se.hig.service.branch;

import se.hig.domain.Person;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Returns a list of every person in that branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class GetPersonsByBranchService extends AbstractBranchService {
    @Override
    public List<Person> execute() throws CleaningManagerServiceException {
        List<Person> personList;
        try {
            branchDao.openConnection();
            personList = branchDao.getPersonsByBranchId(branch.getId());
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve persons by branch ID", e);
        } finally {
            branchDao.closeConnection();
        }
        return personList;
    }

}
