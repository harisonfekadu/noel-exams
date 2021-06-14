package logic.errorManagment;
public class QuestionExists extends Exception{
    //  Fields
    private int questionNumber;
    //  Constructors
    public QuestionExists(int questionNumber){
        this.questionNumber=questionNumber;
    }
    //  Methods
    public int getQuestionNumber() {
        return questionNumber;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return "Question "+this.questionNumber+" was found.";
    }
}
