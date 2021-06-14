package uiComponents.makeExam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.dataModules.Answer;
import logic.dataStrucures.dataModules.Choice;
import logic.dataStrucures.dataModules.Question;
import logic.errorManagment.Validator;

public class MakeQuestionPane extends VBox{
    // Fields
    public int questionNumber;
    public Label questionNumberLabel;
    public Button deleteButton;
    public ToolBar headPanel;
    public TextArea questionField;
    public Label pointLabel;
    public TextField pointField;
    public Label timeLabel;
    public TextField timeField;
    public VBox rightPanel;
    public HBox questionPanel;
    public RadioButton choiceAButton;
    public TextField choiceAField;
    public HBox choiceAPanel;
    public RadioButton choiceBButton;
    public TextField choiceBField;
    public HBox choiceBPanel;
    public RadioButton choiceCButton;
    public TextField choiceCField;
    public HBox choiceCPanel;
    public RadioButton choiceDButton;
    public TextField choiceDField;
    public HBox choiceDPanel;
    public ToggleGroup choiceToggleGroup;
    public VBox choicePanel;
    public Label messageLabel;
    public Question question;

    // Constructors
    public MakeQuestionPane(int questionNumber){
        this.questionNumber=questionNumber;
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(830);
        setSpacing(8);
        setAlignment(Pos.CENTER);
    }
    // Methods
    public void initialize(){
        questionNumberLabel=new Label(Integer.toString(questionNumber));
        questionNumberLabel.setFont(new Font(20));
        questionNumberLabel.setAlignment(Pos.TOP_LEFT);
        /*deleteButton=new Button("Delete");
        deleteButton.setAlignment(Pos.TOP_LEFT);*/
        pointLabel=new Label("Point:");
        pointField=new TextField(Double.toString(Question.defaultPoint));
        timeLabel=new Label("Time:");
        timeField=new TextField(Double.toString(Question.defaultTime));
        headPanel=new ToolBar(questionNumberLabel,new Separator(),pointLabel,pointField,new Separator(),timeLabel,timeField);

        questionField=new TextArea();
        questionField.setPrefWidth(400);
        questionField.setPrefHeight(400);
        /*questionPanel=new HBox();
        questionPanel.getChildren().addAll(questionField,rightPanel);
*/
        choiceAButton=new RadioButton("A: ");
        choiceAField=new TextField();
        choiceAField.setPrefWidth(600);
        choiceAPanel=new HBox();
        choiceAPanel.getChildren().addAll(choiceAButton,choiceAField);
        choiceBButton=new RadioButton("B: ");
        choiceBField=new TextField();
        choiceBField.setPrefWidth(600);
        choiceBPanel=new HBox();
        choiceBPanel.getChildren().addAll(choiceBButton,choiceBField);
        choiceCButton=new RadioButton("C: ");
        choiceCField=new TextField();
        choiceCField.setPrefWidth(600);
        choiceCPanel=new HBox();
        choiceCPanel.getChildren().addAll(choiceCButton,choiceCField);
        choiceDButton=new RadioButton("D: ");
        choiceDField=new TextField();
        choiceDField.setPrefWidth(600);
        choiceDPanel=new HBox();
        choiceDPanel.getChildren().addAll(choiceDButton,choiceDField);
        choiceToggleGroup=new ToggleGroup();
        choiceAButton.setToggleGroup(choiceToggleGroup);
        choiceBButton.setToggleGroup(choiceToggleGroup);
        choiceCButton.setToggleGroup(choiceToggleGroup);
        choiceDButton.setToggleGroup(choiceToggleGroup);
        choiceAButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceBButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceCButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceDButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceAButton.setSelected(true);
        choicePanel=new VBox();
        choicePanel.getChildren().addAll(choiceAPanel,choiceBPanel,choiceCPanel,choiceDPanel);
        choicePanel.setSpacing(8);

        messageLabel=new Label();
        getChildren().addAll(headPanel,questionField,choicePanel,messageLabel);
    }
    public boolean finishQuestion(){
        report("");
        double point=Question.defaultPoint;;
        double time=Question.defaultTime;
        String questionFieldText=questionField.getText();
        Choice choiceA,choiceB,choiceC,choiceD;
        if(!Validator.validatePoint(pointField.getText())){
            report("Invalid point for question.");
            return false;
        } else {
            if(!pointField.getText().isEmpty()){
                point=Double.parseDouble(pointField.getText());
            }
        }
        if(!Validator.validateTime(timeField.getText())){
            report("Invalid time for question.");
            return false;
        } else {
            if(timeField.getText().isEmpty()){
                time=Double.parseDouble(timeField.getText());
            }
        }
        char correctChoice;
        if(choiceAButton.isPressed()){
            correctChoice='A';
        } else if(choiceBButton.isPressed()){
            correctChoice='B';
        } else if(choiceCButton.isPressed()){
            correctChoice='C';
        } else {
            correctChoice='D';
        }
        if(!Validator.validateQuestionNumber(questionNumber)){
            report("Invalid Question Number.");
            return false;
        }
        if(questionFieldText.isEmpty()){
            report("Please fill all fields");
            return false;
        }
        try {
            if(choiceAField.getText().isEmpty()){
                report("Please fill all fields");
                return false;
            } else {
                choiceA=new Choice(choiceAField.getText(),'A');
            }
            if(choiceBField.getText().isEmpty()){
                report("Please fill all fields");
                return false;
            } else {
                choiceB=new Choice(choiceBField.getText(),'B');
            }
            if(choiceCField.getText().isEmpty()){
                report("Please fill all fields");
                return false;
            } else {
                choiceC=new Choice(choiceCField.getText(),'C');
            }
            if(choiceDField.getText().isEmpty()){
                report("Please fill all fields");
                return false;
            } else {
                choiceD=new Choice(choiceDField.getText(),'D');
            }
            Answer answer=new Answer(choiceA,choiceB,choiceC,choiceD,correctChoice);
            question=new Question(questionFieldText,answer,point,time);
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
