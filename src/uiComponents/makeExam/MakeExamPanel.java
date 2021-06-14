package uiComponents.makeExam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.User;

public class MakeExamPanel extends VBox{
    // Fields
    LinkedList<MakeQuestionPane> questionPaneList;
    public TextField nameField;
    public Button addQuestionButton;
    public Button finishButton;
    public Button cancelButton;
    public ToolBar navBar;
    public ScrollPane scrollPane;
    public VBox body;
    public Exam exam;
    public String examName;
    public String instruction;
    public User user;
    public Label messageLabel;
    public Label instructionLabel;
    public TextArea instructionField;
    public VBox instructionPane;
    public static int QUESTION_COUNT=0;
    // Constructors
    public MakeExamPanel(String examName, User user){
        this.examName=examName;
        this.user=user;
        QUESTION_COUNT=0;
        questionPaneList=new LinkedList<MakeQuestionPane>(new MakeQuestionPane(++QUESTION_COUNT));
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(850);
        setPrefHeight(850);
        setSpacing(8);
        setAlignment(Pos.TOP_CENTER);
    }
    // Methods
    public void initialize(){
        nameField =new TextField(examName);
        nameField.setFont(new Font(20));
        addQuestionButton=new Button("Add Question");
        cancelButton=new Button("Cancel");
        finishButton=new Button("Finish");
        navBar=new ToolBar(nameField,new Separator(),addQuestionButton,new Separator(),cancelButton,finishButton);

        instructionLabel=new Label("Set instrucion: ");
        instructionField=new TextArea();
        instructionPane=new VBox();
        messageLabel=new Label();
        instructionPane.getChildren().addAll(instructionLabel,instructionField,messageLabel);

        body=new VBox();
        body.setAlignment(Pos.TOP_CENTER);
        scrollPane=new ScrollPane(body);
        body.getChildren().add(instructionPane);
        try {
            body.getChildren().add(questionPaneList.get(0));
        } catch (Exception e){
            System.out.println(e.toString());
        }

        addQuestionButton.setOnAction(event -> {
            MakeQuestionPane questionPane=new MakeQuestionPane(++QUESTION_COUNT);
            questionPaneList.add(questionPane);
            body.getChildren().add(questionPane);
        });
        getChildren().addAll(navBar,scrollPane);
    }
    public boolean finishExam(){
        try {
            if(instructionField.getText().isEmpty()){
                report("Please fill in the instruction.");
                return false;
            }
            instruction=instructionField.getText();
            for(int i=0; i<questionPaneList.size();i++){
                System.out.println(i);
                if(!questionPaneList.get(i).finishQuestion()){
                    return false;
                }
            }
            exam=new Exam(examName,user.getNameObject());
            exam.setInstructions(instruction);
            for(int i=0; i<questionPaneList.size();i++){
                exam.addQuestion(questionPaneList.get(i).question);
            }
            return true;
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }
    public void report(String message){
        messageLabel.setText(message);
    }


}
