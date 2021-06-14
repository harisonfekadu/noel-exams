package logic.errorManagment;
public class InvalidQuestionNumber extends Exception{
    //  Fields
    private int questionNumber;
    //  Constructors
    public InvalidQuestionNumber(int questionNumber){
        this.questionNumber = questionNumber;
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
        return this.questionNumber +" is an invalid Question number.";
    }
}
