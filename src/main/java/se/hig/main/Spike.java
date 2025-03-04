package se.hig.main;

import se.hig.domain.Branch;
import se.hig.domain.Person;
import se.hig.repository.PersonDao;
import se.hig.service.CleaningManagerServiceException;
import se.hig.service.PersonService;
import se.hig.service.branch.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main method demoing the different layers.
 *
 * @author Simon Fedko
 * @version 2025
 */



public class Spike {
    public static void main(String[] args) throws SQLException, CleaningManagerServiceException {

        System.out.println("List of branches: ");
        List<Branch> branchList = new ArrayList<>();
        branchList = new GetAllBranchService().execute();

        for (Branch branch : branchList) {
            System.out.println(branch);
        }

        System.out.println("Branch with ID 1: ");
        System.out.println(new GetBranchService(branchList.get(0)));

        System.out.println("Adding branch:");

        Branch newBranch = new Branch("Vesuvio", "Newark");
        Branch completeBranch = new SaveBranchService(newBranch).execute();
        System.out.println(completeBranch);

        System.out.println("Updating branch: ");
        completeBranch.setName("Pizza Land");
        System.out.println(new UpdateBranchService(completeBranch).execute());

        System.out.println("Deleting branch: ");
        System.out.println(new DeleteBranchService(completeBranch).execute());

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


