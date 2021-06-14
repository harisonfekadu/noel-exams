package logic.errorManagment;
public class QuestionNotFound extends Exception{
    //  Fields
    private int questionNumber;
    //  Constructors
    public QuestionNotFound(int questionNumber){
        this.questionNumber=questionNumber;
    }
    //  Methods
    public int getQuestionNumber() {
        return questionNumber;
    }
    public void display(){
        System.out.println("Question "+this.questionNumber+" was not found.");
    }
    @Override
    public String toString(){
        return "Question "+this.questionNumber+" was not found.";
    }
}
