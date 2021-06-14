package logic.dataStrucures.dataModules;
import logic.errorManagment.*;

public class Question {
    //  Fields
    private int questionNumber;
    private static int QUESTION_COUNT=0;
    private String questionField;
    private Answer answer;
    private char usersAnswer;
    private double point;
    private double time;
    public static double defaultPoint=1.00;
    public static double defaultTime=1.00;
    private boolean isCorrect;
    public static final Question PLACE_HOLDER=new Question("Inser Question Here...");
    //  Constructors
    public Question(String questionField, Answer answer, double point, double time){
        this.questionField=questionField;
        this.point=point;
        this.time=time;
        this.questionNumber=++Question.QUESTION_COUNT;
        this.answer=answer;
        this.usersAnswer='\0';
    }
    public Question(String questionField){
        this.questionField=questionField;
        this.point= Question.defaultPoint;
        this.time= Question.defaultTime;
        this.answer=Answer.PLACE_HOLDER;
        this.questionNumber=++Question.QUESTION_COUNT;
    }
    //  Methods
    public String getAnswer() {
        return answer.toString();
    }
    public Answer getAnswerObject() {
        return answer;
    }
    public void setAnswer(String choiceA, String choiceB, String choiceC, String choiceD, char correctChoice) throws ChoiceExists,ChoiceOutOfBounds,InvalidChoiceId{
        try {
            this.answer.setCorrectChoice(correctChoice);
            this.answer.setChoice('A',choiceA);
            this.answer.setChoice('B',choiceB);
            this.answer.setChoice('C',choiceC);
            this.answer.setChoice('D',choiceD);
        } catch (ChoiceOutOfBounds e){
            throw new ChoiceOutOfBounds(e.getChoiceField(),e.getChoiceId());
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        } catch (ChoiceExists e){
            throw new ChoiceExists(e.getChoiceId());
        } catch (NullPointerException e){
            System.out.println(e.toString());
        }
    }
    public char getCorrectAnswer() {
        return answer.getCorrectChoice();
    }
    public char getUsersAnswer() {
        return usersAnswer;
    }
    public double getPoint() {
        return point;
    }
    public double getTime() {
        return time;
    }
    public String getQuestionField() {
        return questionField;
    }
    public void setPoint(double point) {
        this.point = point;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public boolean setUsersAnswer(char usersAnswer) throws InvalidChoiceId{
        if(!Validator.validateChoice(usersAnswer)){
            throw new InvalidChoiceId(usersAnswer);
        }
        this.usersAnswer = usersAnswer;
        if(usersAnswer==this.answer.getCorrectChoice()){
            this.isCorrect=true;
        } else {
            this.isCorrect=false;
        }
        return this.isCorrect;
    }
    public void setQuestionField(String questionField) {
        this.questionField = questionField;
    }
    public void overrideQuestionNumber(int questionNumber) throws InvalidQuestionNumber{
        if(Validator.validateQuestionNumber(questionNumber)){
            this.questionNumber = questionNumber;
        } else {
            throw new InvalidQuestionNumber(questionNumber);
        }
    }
    public boolean isCorrect(){
        return isCorrect;
    }
    public boolean isCorrect(char usersAnswer) throws InvalidChoiceId{
        try {
            return this.setUsersAnswer(usersAnswer);
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        }
    }
    public double getGrade(){
        if(isCorrect()){
            return this.getPoint();
        } else {
            return 0.0;
        }
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
    @Override
    public String toString(){
        return questionField+"\n"+getAnswer();
    }
}
