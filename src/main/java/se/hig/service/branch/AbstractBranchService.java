package se.hig.service.branch;

import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

/**
 * Abstract class for BranchServices to inherit.
 *
 * @author Simon Fedko
 * @version 2025
 */

abstract class AbstractBranchService implements BranchServiceInterface {
    BranchDao branchDao;
    Branch branch;

    public AbstractBranchService() {
        this(new BranchDao());
    }

    public AbstractBranchService(BranchDao branchDao) {
        this(branchDao, null);
    }

    public AbstractBranchService(Branch branch) {
        this(new BranchDao(), branch);
    }

    public AbstractBranchService(BranchDao branchDao, Branch branch) {
        this.branchDao = branchDao;
        this.branch = branch;
    }
}
