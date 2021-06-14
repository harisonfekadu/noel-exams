package logic.dataStrucures.dataModules;
import logic.dataStrucures.structureModules.LinkedList;
import logic.errorManagment.*;
import logic.dataStrucures.userModules.PersonName;

public class Exam {
    //  Fields
    private String examName;
    private String instructions;
    private LinkedList<Question> questions;
    private double time;
    private double point;
    private double grade;
    private int numberOfQuestions;
    public static final Exam PLACE_HOLDER=new Exam("Insert Exam Title Here...", PersonName.PLACE_HOLDER);
    private PersonName author;
    //  Constructors
    public Exam(String examName, String instructions, LinkedList<Question> questions, PersonName author){
        this.examName = examName;
        this.instructions=instructions;
        this.questions=questions;
        reevaluateFields();
        this.author=author;
    }
    public Exam(String examName,PersonName author){
        this.examName = examName;
        this.instructions="Insert Instruction Here...";
        this.author=author;
        this.questions=new LinkedList<Question>(Question.PLACE_HOLDER);
        reevaluateFields();
    }
    //  Methods
    public static double renderPoints(LinkedList<Question> questions) throws NodeNotFound{
        double point=0;
        for (int i=0; i<questions.size();i++){
            try {
                point+=questions.get(i).getPoint();
            } catch (NodeNotFound e){
                throw new NodeNotFound(e.getIndex());
            }
        }
        return point;
    }
    public static double renderTime(LinkedList<Question> questions) throws NodeNotFound{
        double time=0;
        for (int i=0; i<questions.size();i++){
            try {
                time+=questions.get(i).getTime();
            } catch (NodeNotFound e){
                throw new NodeNotFound(e.getIndex());
            }
        }
        return time;
    }
    public static double renderGrade(LinkedList<Question> questions) throws NodeNotFound{
        double grade=0;
        for (int i=0; i<questions.size();i++){
            if(questions.get(i).isCorrect()){
                try {
                    grade+=questions.get(i).getPoint();
                } catch (NodeNotFound e){
                    throw new NodeNotFound(e.getIndex());
                }
            }
        }
        return grade;
    }
    public Question getQuestion(int questionNumber) throws QuestionNotFound {
        try {
            for(int i=0; i<questions.size(); i++){
                if(questions.get(i).getQuestionNumber()==questionNumber){
                    return questions.get(i);
                }
            }
        } catch (NodeNotFound e){
            throw new QuestionNotFound(questionNumber);
        }
        throw new QuestionNotFound(questionNumber);
    }
    public Question deleteQuestion(int questionNumber) throws QuestionNotFound {
        try {
            for(int i=0; i<questions.size(); i++){
                if(questions.get(i).getQuestionNumber()==questionNumber){
                    Question deletedQuestion=questions.remove(i);
                    this.sequenceQuestionNumbers();
                    return deletedQuestion;
                }
            }
        } catch (NodeNotFound e){
            throw new QuestionNotFound(questionNumber);
        }
        throw new QuestionNotFound(questionNumber);
    }
    public void reevaluateFields(){
        try {
            this.point= Exam.renderPoints(this.questions);
            this.time= Exam.renderTime(this.questions);
            this.grade= Exam.renderGrade(this.questions);
            this.numberOfQuestions=this.numberOfQuestions();
        } catch (NodeNotFound e){
            e.display();
        }

    }
    public void addQuestion(Question question) throws QuestionExists {
        if(!DuplicateManager.doesQuestionExist(this.questions,question)){
            if(!DuplicateManager.doesQuestionExist(this.questions,question.getQuestionNumber())) {
                this.questions.add(question);
            } else {
                try {
                    question.overrideQuestionNumber(question.getQuestionNumber()+1);
                } catch (Exception e){
                    //no-op
                }
                addQuestion(question);
            }
        } else {
            throw new QuestionExists(question.getQuestionNumber());
        }
        this.sequenceQuestionNumbers();
        this.reevaluateFields();
    }
    public double getTime() {
        this.reevaluateFields();
        return time;
    }
    public double getPoint() {
        this.reevaluateFields();
        return point;
    }
    public double getGrade() {
        this.reevaluateFields();
        return grade;
    }
    public double getGradePercent() {
        this.reevaluateFields();
        return (grade/point)*100;
    }
    public void setAuthor(String firstName, String lastName) throws InvalidName {
        try {
            this.author =new PersonName(firstName,lastName);
        } catch (InvalidName e){
            throw new InvalidName(e.getName());
        }
    }
    public void setAuthor(PersonName authorName){
        this.author=authorName;
    }
    public PersonName getAuthor() {
        return author;
    }
    public void setExamName(String examName) {
        this.examName = examName;
    }
    public String getExamName() {
        return examName;
    }
    public int numberOfQuestions() {
        if(this.questions.isEmpty()){
            this.numberOfQuestions=0;
        } else {
            this.numberOfQuestions=this.questions.size();
        }
        return this.numberOfQuestions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getInstructions() {
        return instructions;
    }
    public void sequenceQuestionNumbers(){
        LinkedList<Question> questionList= new LinkedList<Question>();
        try {
            for(int i=0; i<this.questions.size();i++){
                Question question=this.questions.get(i);
                question.overrideQuestionNumber(i+1);
                questionList.add(question);
            }
            this.questions=questionList;
        } catch (Exception e){
            // --
        }
    }
    public LinkedList<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }
    public String export(){
        sequenceQuestionNumbers();
        String exam=this.examName+"\t by "+this.author+"\n"+this.getInstructions()+"\n";
        try {
            for (int i=1;i<=this.numberOfQuestions();i++){
                exam=exam.concat(this.getQuestion(i).getQuestionNumber()+") "+this.getQuestion(i)+"\n");
            }
        } catch (Exception e){
            // --
        }
        return exam;
    }
    @Override
    public String toString(){
        return this.examName+"\t by "+this.author;
    }
}

