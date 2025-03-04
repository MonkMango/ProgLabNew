package se.hig.service.branch;

import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.repository.BranchDao;
import se.hig.repository.DaoFactory;

/**
 * Abstract class for BranchServices to inherit.
 *
 * @author Simon Fedko
 * @version 2025
 */

abstract class AbstractBranchService implements BranchServiceInterface {
    protected DaoFactory factory;
    protected DbConnectionManager dbConn;
    protected final Branch branch;

    public AbstractBranchService() {
        this(new DaoFactory());
    }

    public AbstractBranchService(DaoFactory factory) {
        this(factory, null);
    }

    public AbstractBranchService(Branch branch) {
        this(new DaoFactory(), branch);
    }

    public AbstractBranchService(DaoFactory factory, Branch branch) {
        init(factory);
        this.branch = branch;
    }

    public void init(DaoFactory factory) {
        this.factory = factory;
    }

}
