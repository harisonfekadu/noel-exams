package uiComponents.takeExam;
import javafx.scene.layout.VBox;
import logic.dataStrucures.dataModules.Question;
import logic.dataStrucures.structureModules.LinkedList;
public class QuestionListPane extends VBox {
    // Fields
    public LinkedList<Question> questionList;
    public LinkedList<QuestionPane> questionPaneList;
    // Constructors
    public QuestionListPane(VBox instructionPane,LinkedList<Question> questionList){
        this.questionList=questionList;
        this.questionPaneList=new LinkedList<QuestionPane>();
        this.getChildren().add(instructionPane);
        try {
            for(int i=0;i<questionList.size();i++){
                addQuestionPane(new QuestionPane(questionList.get(i)));
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    // Methods
    public void addQuestionPane(QuestionPane questionPane){
        questionPaneList.add(questionPane);
        this.getChildren().add(questionPane);
    }
    public LinkedList<Question> finishQuestions(){
        LinkedList<Question> questionList=new LinkedList<Question>();
        try {
            for(int i=0; i<questionPaneList.size();i++){
                questionList.add(questionPaneList.get(i).finishQuestion());
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return questionList;
    }
}
