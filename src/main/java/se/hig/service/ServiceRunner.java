package se.hig.service;

import se.hig.db.DbConnectionManager;
import se.hig.repository.DaoFactory;
import se.hig.service.branch.BranchServiceInterface;

/**
 * Class that runs services, handles database-connections och converts exceptions.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class ServiceRunner {

    /*
    private BranchServiceInterface service;

    public void setCommand(BranchServiceInterface service) {
        this.service = service;
    }
*/

    public <T> T execute(BranchServiceInterface service) throws CleaningManagerServiceException {
        T result;
        DbConnectionManager.getInstance().open();
        try {
            service.init(new DaoFactory());
            result = service.execute();
        } catch (CleaningManagerServiceException e) {
            throw new RuntimeException(e);
        } finally {
            DbConnectionManager.getInstance().close();
        }

        return result;
    }

}
