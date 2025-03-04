package se.hig.service.branch;

import se.hig.db.DbConnectionManager;
import se.hig.repository.DaoFactory;
import se.hig.service.CleaningManagerServiceException;

/**
 * Interface with a single method for the BranchServices to implement.
 *
 * @author Simon Fedko
 * @version 2025
 */

public interface BranchServiceInterface {
    public <T> T execute() throws CleaningManagerServiceException;
    public void init(DaoFactory factory);
}
