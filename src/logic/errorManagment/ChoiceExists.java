package logic.errorManagment;

public class ChoiceExists extends Exception {
    //  Fields
    private char choiceId;

    //  Constructors
    public ChoiceExists(char choiceId) {
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
        return "Choice " + this.choiceId + " exists.";
    }
}
