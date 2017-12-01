package savagavran.blorgreader.shared;

public abstract class RepositorySpecification<T> {

    private T id;

    public T getID() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
