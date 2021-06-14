package logic.errorManagment;

public class ChoiceNotFound extends Exception {
    //  Fields
    private char choiceId;

    //  Constructors
    public ChoiceNotFound(char choiceId) {
        this.choiceId = choiceId;
    }

    //  Methods
    public char getChoiceId() {
        return this.choiceId;
    }

    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Choice " + this.choiceId + " was not found.";
    }
}
