package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.hig.domain.Branch;
import se.hig.service.CleaningManagerServiceException;
import se.hig.service.ServiceRunner;
import se.hig.service.branch.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Integration Test covering different layers.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class IntegrationTest {
    private ServiceRunner runner;

    @BeforeEach
    void setUp() {
        runner = new ServiceRunner();
    }

    @Test
    void integrationOfBranchServices() throws CleaningManagerServiceException {

        GetAllBranchService getAllBranchService = new GetAllBranchService();
        List<Branch> branchList = runner.execute(getAllBranchService);
        assertNotNull(branchList);

        assertFalse(branchList.isEmpty());
        GetBranchService getBranchService = new GetBranchService(branchList.get(0));
        Branch retrievedBranch = runner.execute(getBranchService);
        assertNotNull(retrievedBranch);

        Branch newBranch = new Branch("Vesuvio", "Newark");
        SaveBranchService saveBranchService = new SaveBranchService(newBranch);
        Branch savedBranch = runner.execute(saveBranchService);
        assertNotNull(savedBranch);
        assertEquals("Vesuvio", savedBranch.getName());

        savedBranch.setName("Pizza Land");
        UpdateBranchService updateBranchService = new UpdateBranchService(savedBranch);
        Branch updatedBranch = runner.execute(updateBranchService);
        assertNotNull(updatedBranch);
        assertEquals("Pizza Land", updatedBranch.getName());

        DeleteBranchService deleteBranchService = new DeleteBranchService(updatedBranch);
        Branch deletedBranch = runner.execute(deleteBranchService);
        assertNotNull(deletedBranch);
        assertEquals(updatedBranch.toString(), deletedBranch.toString());
    }
}
