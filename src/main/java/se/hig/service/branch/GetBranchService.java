package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.service.CleaningManagerServiceException;

import java.sql.SQLException;

/**
 * Handles the get operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class GetBranchService extends AbstractBranchService {

    public GetBranchService(Branch branch) {
        super(branch);
    }

    public GetBranchService(BranchDao branchDao, Branch branch) {
        super(branchDao, branch);
    }

    public Branch execute() throws CleaningManagerServiceException {
        Branch retrievedBranch;
        try {
            branchDao.openConnection();
            retrievedBranch = branchDao.get(branch.getId());
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve branch", e);
        } finally {
            branchDao.closeConnection();
        }
        return retrievedBranch;
    }


}
