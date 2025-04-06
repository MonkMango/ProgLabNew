package se.hig.service.branch;


import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.repository.DaoFactory;
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

    public GetBranchService(DaoFactory factory, Branch branch) {
        super(factory, branch);
    }

    public Branch execute() throws CleaningManagerServiceException {
        Branch retrievedBranch;
        try {
            retrievedBranch = (Branch) factory.getBranchDao().get(branch.getId()).orElseThrow();
        } catch (SQLException e) {
            throw new CleaningManagerServiceException("Failed to retrieve branch", e);
        }
        return retrievedBranch;
    }

}
