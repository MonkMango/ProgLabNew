package se.hig.service.branch;


import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.repository.DaoFactory;
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
            deletedBranch = factory.getBranchDao().delete(branch).orElseThrow();
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to delete branch", e);
        }
        return deletedBranch;
    }

}
