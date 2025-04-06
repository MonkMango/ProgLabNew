package service;


import org.junit.jupiter.api.Test;
import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.BranchDao;
import se.hig.repository.DaoFactory;
import se.hig.service.CleaningManagerServiceException;
import se.hig.service.branch.GetBranchService;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetBranchServiceTest {

    Branch testBranch = new Branch(1, "Bada Bong", "Newark");
    BranchDao branchDaoMock = mock(BranchDao.class);
    GetBranchService getBranchService = new GetBranchService(new DaoFactory(), testBranch);

    @Test
    void getBranchServiceExecute() throws SQLException, CleaningManagerServiceException {
        getBranchService.execute();
        when(branchDaoMock.get(testBranch.getId())).thenReturn(Optional.ofNullable(testBranch));
        Branch result = getBranchService.execute();
        assertTrue(result instanceof Branch);
        assertEquals("Bada Bong", result.getName());
        verify(branchDaoMock, times(1)).get(1);
    }


}