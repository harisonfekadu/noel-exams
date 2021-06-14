package uiComponents.takeExam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.dataModules.Answer;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Question;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.User;
import uiComponents.Timer;

public class ExamPane extends VBox{
    // Fields
    public Label examName;
    public Label examPoint;
    public Label examTime;
    public Button finishButton;
    public Button cancelButton;
    public ToolBar navBar;
    public Exam exam;
    public User user;
    public TextArea instructionField;
    public VBox instructionPane;
    public ScrollPane scrollPane;
    public QuestionListPane body;
    // Constructors
    public ExamPane(Exam exam, User user,boolean isStudent){
        this.exam=exam;
        this.user=user;
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(850);
        setPrefHeight(850);
        setSpacing(8);
        setAlignment(Pos.TOP_CENTER);
    }
    // Methods
    public void initialize(){
        examName =new Label(exam.getExamName());
        examName.setFont(new Font(20));
        examPoint=new Label("Point: "+exam.getPoint());
        examTime=new Timer(exam.getTime());
        cancelButton=new Button("Cancel");
        finishButton=new Button("Finish");
        navBar=new ToolBar(examName,new Separator(),examPoint,new Separator(),examTime,new Separator(),cancelButton,finishButton);

        instructionField=new TextArea(exam.getInstructions());
        instructionPane=new VBox();
        instructionPane.getChildren().addAll(instructionField);

        body=new QuestionListPane(instructionPane,exam.getQuestions());
        scrollPane=new ScrollPane(body);

        getChildren().addAll(navBar,scrollPane);
    }
    public void finishExam(){
        exam.setQuestions(body.finishQuestions());
        //exam.reevaluateFields();
        System.out.println(exam.export());
    }

}
