package se.hig.service.branch;

import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

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

    public Branch execute() {
        return branchDao.save(branch);
    }

}
