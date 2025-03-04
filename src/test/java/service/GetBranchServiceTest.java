package service;


import org.junit.jupiter.api.Test;
import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.BranchDao;
import se.hig.service.CleaningManagerServiceException;
import se.hig.service.branch.GetBranchService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetBranchServiceTest {

    Branch testBranch = new Branch(1, "Bada Bong", "Newark");
    BranchDao branchDaoMock = mock(BranchDao.class);
    GetBranchService getBranchService = new GetBranchService(branchDaoMock, testBranch);

    @Test
    void getBranchServiceExecute() throws SQLException, CleaningManagerServiceException {
        when(branchDaoMock.get(testBranch.getId())).thenReturn(testBranch);
        Branch result = getBranchService.execute();
        assertTrue(result instanceof Branch);
        assertEquals("Bada Bong", result.getName());
        verify(branchDaoMock, times(1)).get(1);
    }


}