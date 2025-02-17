package se.hig.service.branch;


import se.hig.domain.Branch;
import se.hig.repository.BranchDao;

import java.util.List;

/**
 * Handles the getAll operation for Branch.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class GetAllBranchService extends AbstractBranchService {

    public List<Branch> execute() {
        return branchDao.getAll();
    }

}
