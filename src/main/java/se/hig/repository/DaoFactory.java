package se.hig.repository;

public class DaoFactory {

    public PersonDao getPersonDao() {
        return new PersonDao();
    }

    public BranchDao getBranchDao() {
        return new BranchDao();
    }

    public < T extends Dao<?>> T get(FactoryType type){
        return (T) type.createDao();
    }

    /*
    public Dao get(FactoryType type) {
        return switch(type){
            case PERSON -> new PersonDao();
            case BRANCH -> new BranchDao();
        };
    }

    */

    public enum FactoryType{
        PERSON {
            @Override
            public PersonDao createDao() {
                return new PersonDao();
            }
        },
        BRANCH {
            @Override
            public BranchDao createDao() {
                return new BranchDao();
            }
        };

        public abstract Dao createDao();
    }

}
