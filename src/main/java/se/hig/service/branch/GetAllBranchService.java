package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Handles the getAll operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class GetAllBranchService extends AbstractBranchService {

    public List<Branch> execute() throws CleaningManagerServiceException {
        List<Branch> branchList;
        try {
            branchDao.openConnection();
            branchList = branchDao.getAll();
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve all branches", e);
        } finally {
            branchDao.closeConnection();
        }
        return branchList;
    }


}
