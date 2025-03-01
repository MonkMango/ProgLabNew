package se.hig.service.branch;

import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;

/**
 * Handles the save operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class SaveBranchService extends AbstractBranchService {

    public SaveBranchService(Branch newBranch) {
        super(newBranch);
    }

    public Branch execute() throws CleaningManagerServiceException {
        Branch savedBranch;
        try {
            branchDao.openConnection();
            savedBranch = branchDao.save(branch);
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to save branch", e);
        } finally {
            branchDao.closeConnection();
        }
        return savedBranch;
    }
}