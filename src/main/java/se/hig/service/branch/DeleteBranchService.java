package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;

/**
 * Handles the delete operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class DeleteBranchService extends AbstractBranchService {

    public DeleteBranchService(Branch branch) {
        super(branch);
    }

    public Branch execute() throws CleaningManagerServiceException {
        Branch deletedBranch;
        try {
            branchDao.openConnection();
            deletedBranch = branchDao.delete(branch);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to delete branch", e);
        } finally {
            branchDao.closeConnection();
        }
        return deletedBranch;
    }

}
