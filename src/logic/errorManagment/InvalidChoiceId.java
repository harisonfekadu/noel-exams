package logic.errorManagment;
public class InvalidChoiceId extends Exception{
    //  Fields
    private char choiceId;
    //  Constructors
    public InvalidChoiceId(char choiceId){
        this.choiceId = choiceId;
    }
    //  Methods
    public char getChoiceId() {
        return choiceId;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return this.choiceId +" is an invalid Choice.";
    }
}
