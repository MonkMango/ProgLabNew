package se.hig.repository;

public class DaoFactory {

    public Dao getPersonDao() {
        return new PersonDao();
    }

    public Dao getBranchDao() {
        return new BranchDao();
    }

    public Dao get(FactoryType type) {
        return switch(type){
            case PERSON -> new PersonDao();
            case BRANCH -> new BranchDao();
        };
    }

    public enum FactoryType{
        PERSON,
        BRANCH
    }

}
