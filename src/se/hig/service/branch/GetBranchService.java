package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

/**
 * Handles the get operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class GetBranchService extends AbstractBranchService {


    public GetBranchService(BranchDao branchDao, Branch branch) {
        super(branchDao, branch);
    }

    public Branch execute() {
        return branchDao.get(branch.getId());
    }

}
