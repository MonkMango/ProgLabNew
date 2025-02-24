package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

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

    public Branch execute() {
        return branchDao.delete(branch);
    }
}
