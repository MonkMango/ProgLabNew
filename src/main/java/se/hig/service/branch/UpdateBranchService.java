package se.hig.service.branch;

import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.repository.DaoFactory;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;

/**
 * Handles the update operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class UpdateBranchService extends AbstractBranchService {

    public UpdateBranchService(Branch completeBranch) {
        super(completeBranch);
    }

    public Branch execute() throws CleaningManagerServiceException {
        Branch updatedBranch;
        try {
            updatedBranch = factory.getBranchDao().update(branch).orElseThrow();
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to update branch", e);
        }
        return updatedBranch;
    }

}
