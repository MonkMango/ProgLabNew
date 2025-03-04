package se.hig.main;

import se.hig.db.DbConnectionManager;
import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.DaoFactory;
import se.hig.service.CleaningManagerServiceException;
import se.hig.service.PersonService;
import se.hig.service.ServiceRunner;
import se.hig.service.branch.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class, demoing the other classes.
 *
 * @author Simon Fedko
 * @version 2025
 */

public class Spike2 {
    public static void main(String[] args) throws SQLException, CleaningManagerServiceException {

        ServiceRunner runner = new ServiceRunner();

        System.out.println("List of branches: ");
        List<Branch> branchList = new ArrayList<>();
        GetAllBranchService getAllBranchService = new GetAllBranchService();

        branchList = runner.execute(getAllBranchService);

        for (Branch branch : branchList) {
            System.out.println(branch);
        }

        System.out.println("Branch with ID 1: ");
        GetBranchService getBranchService = new GetBranchService(branchList.get(0));
        Branch gottenBranch = runner.execute(getBranchService);
        System.out.println(gottenBranch);

        System.out.println("Adding branch:");

        Branch newBranch = new Branch("Vesuvio", "Newark");
        SaveBranchService saveBranchService = new SaveBranchService(newBranch);
        Branch completeBranch = runner.execute(saveBranchService);
        System.out.println(completeBranch);

        System.out.println("Updating branch: ");
        completeBranch.setName("Pizza Land");
        UpdateBranchService updateBranchService = new UpdateBranchService(completeBranch);
        Branch updatedBranch = runner.execute(updateBranchService);
        System.out.println(updatedBranch);

        System.out.println("Deleting branch: ");
        DeleteBranchService deleteBranchService = new DeleteBranchService(completeBranch);
        Branch deletedBranch = runner.execute(deleteBranchService);
        System.out.println(deletedBranch);

        List<Person> peopleList = new ArrayList<>();
        PersonService personService = new PersonService();

    /*
        peopleList = personService.getAllPersons();

        System.out.println("List of people: ");
        for (Person person : peopleList) {
            System.out.println(person);
        }



        System.out.println("Person with ID 1: ");
        System.out.println(personService.getPerson(1));


        System.out.println("Adding person:");

        Person newPerson = new Person("Klaus Kinski", 1964, completeBranch);
        Person completePerson = personService.savePerson(newPerson);
        System.out.println(completePerson);

        System.out.println("Updating person: ");

        completePerson.setName("Al Pacino");
        System.out.println(personService.updatePerson(completePerson));

        System.out.println("Deleting person: ");
        System.out.println(personService.deletePerson(completePerson));

       */

    }
}
