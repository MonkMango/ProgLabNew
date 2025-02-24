package se.hig.service.branch;

import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

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

    public Branch execute() {
        return branchDao.update(branch);
    }
}
