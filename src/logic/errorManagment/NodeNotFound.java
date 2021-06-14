package logic.errorManagment;

public class NodeNotFound extends Exception {
    //  Fields
    private int index;

    //  Constructors
    public NodeNotFound(int index) {
        this.index = index;
    }

    //  Methods
    public int getIndex() {
        return this.index;
    }

    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Node at index " + this.index + " was not found.";
    }
}
